package kr.movements.lunchorder.handler

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.movements.lunchorder.util.JwtProvider
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

/**
 * packageName : kr.movements.lunchorder.handler
 * fileName    : OAuth2SuccessHandler
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */

class AuthenticationSuccessHandlerImpl(
    private val jwtProvider: JwtProvider
): AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val accessToken = jwtProvider.createToken(authentication)
        response.addCookie(createTokenCookie("a", accessToken))
        response.addCookie(createTokenCookie("r", accessToken))
        response.sendRedirect("${request.contextPath}/swagger-ui/index.html")
    }

    private fun createTokenCookie(name: String, token: String): Cookie {
        return Cookie(name, token).apply {
            this.path = "/"
            this.isHttpOnly = true
            this.maxAge = 600
        }
    }
}