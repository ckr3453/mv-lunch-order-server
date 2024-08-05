package kr.movements.lunchorder.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

/**
 * packageName : kr.movements.lunchorder.handler
 * fileName    : AuthenticationEntryPointImpl
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */
class AuthenticationEntryPointImpl: AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }
}