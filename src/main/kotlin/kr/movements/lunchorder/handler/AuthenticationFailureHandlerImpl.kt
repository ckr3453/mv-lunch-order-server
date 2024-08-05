package kr.movements.lunchorder.handler

import com.google.gson.Gson
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import java.nio.charset.StandardCharsets

/**
 * packageName : kr.movements.lunchorder.handler
 * fileName    : AuthenticationFailureHandlerImpl
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */

class AuthenticationFailureHandlerImpl: AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.writer.write(Gson().toJson(AuthenticationFailResponse(exception.message)))
    }
}

data class AuthenticationFailResponse(
    val errorMessage: String?,
)