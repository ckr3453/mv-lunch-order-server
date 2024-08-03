package kr.movements.lunchorder.handler

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.movements.lunchorder.util.TokenProvider
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

/**
 * packageName : kr.movements.lunchorder.handler
 * fileName    : AuthenticationSuccessHandlerImpl
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */

class AuthenticationSuccessHandlerImpl(
    private val tokenProvider: TokenProvider
): AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val accessToken = tokenProvider.createToken(authentication)
        response.addCookie(createCookie("a", accessToken))
        response.addCookie(createCookie("r", accessToken))
        response.sendRedirect("${request.contextPath}/swagger-ui/index.html")
    }

    private fun createCookie(name: String, token: String): Cookie {
        return Cookie(name, token).apply {
            this.path = "/"
            this.isHttpOnly = true
            this.maxAge = 600
        }
    }
}