package kr.movements.lunchorder.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.movements.lunchorder.component.JwtProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

/**
 * packageName : kr.movements.lunchorder.filter
 * fileName    : JwtFilter
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getHeader(AUTHORIZATION_HEADER)
        if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
            val authentication = jwtProvider.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }
}