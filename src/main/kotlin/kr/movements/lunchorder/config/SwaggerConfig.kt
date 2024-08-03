package kr.movements.lunchorder.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * packageName : kr.movements.lunchorder.config
 * fileName    : SwaggerConfig
 * author      : ckr
 * date        : 24. 8. 3.
 * description :
 */
@Configuration
@OpenAPIDefinition(info = Info(title = "MV Lunch Order REST API", description = "", version = "0.0.1"))
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val securityScheme: SecurityScheme = SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .`in`(SecurityScheme.In.HEADER)
            .name("Authorization")
        val securityRequirement: SecurityRequirement = SecurityRequirement().addList("Authorization")
        return OpenAPI()
            .components(Components().addSecuritySchemes("Authorization", securityScheme))
            .security(listOf(securityRequirement))
    }
}