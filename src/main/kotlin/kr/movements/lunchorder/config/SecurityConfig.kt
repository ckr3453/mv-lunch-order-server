package kr.movements.lunchorder.config

import kr.movements.lunchorder.filter.JwtAuthenticationFilter
import kr.movements.lunchorder.handler.AccessDeniedHandlerImpl
import kr.movements.lunchorder.handler.AuthenticationEntryPointImpl
import kr.movements.lunchorder.handler.AuthenticationFailureHandlerImpl
import kr.movements.lunchorder.handler.AuthenticationSuccessHandlerImpl
import kr.movements.lunchorder.service.CustomOAuth2UserService
import kr.movements.lunchorder.util.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * packageName : kr.movements.lunchorder.config
 * fileName    : SecurityConfig
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val jwtProvider: JwtProvider,
    private val customOAuth2UserService: CustomOAuth2UserService,
){

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { it.ignoring().requestMatchers(*PERMIT_ALL_LIST) }
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors {
                corsConfigurationSource()
            }
            .csrf { csrfConfigurer ->
                csrfConfigurer.disable()
            }
            .exceptionHandling { exceptionHandlingConfigurer ->
                exceptionHandlingConfigurer.accessDeniedHandler(AccessDeniedHandlerImpl())
                exceptionHandlingConfigurer.authenticationEntryPoint(AuthenticationEntryPointImpl())
            }
            .sessionManagement { sessionManagementConfigurer ->
                sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authorizeHttpRequestsConfigurer ->
                authorizeHttpRequestsConfigurer.requestMatchers(*PERMIT_ALL_LIST).permitAll()
            }
            .oauth2Login { oauth2LoginConfigurer ->
                oauth2LoginConfigurer.failureHandler(AuthenticationFailureHandlerImpl())
                oauth2LoginConfigurer.successHandler(AuthenticationSuccessHandlerImpl(jwtProvider))
                oauth2LoginConfigurer.userInfoEndpoint { userInfoEndPointConfig ->
                    userInfoEndPointConfig.userService(customOAuth2UserService)
                }
            }
            .addFilterBefore(JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedOriginPattern("*")
        corsConfiguration.addAllowedHeader("*")
        corsConfiguration.addAllowedMethod("*")
        corsConfiguration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }

    companion object {
        private val PERMIT_ALL_LIST = arrayOf(
            "/favicon.ico", "/error", "/v2/api-docs", "/v3/api-docs/**",
            "/swagger-resources/**", "/swagger-ui.html", "/webjars/**",
            "/swagger/**", "/swagger-ui/**", "/h2-console/**", "/test/**"
        )
    }
}