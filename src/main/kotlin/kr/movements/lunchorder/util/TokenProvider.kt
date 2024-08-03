package kr.movements.lunchorder.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.jwt.JwtException
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors
import javax.crypto.SecretKey

/**
 * packageName : kr.movements.lunchorder.component
 * fileName    : TokenProvider
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */
@Component
class TokenProvider(
    @Value("\${jwt.secret}")
    secretKey: String
) {
    private val key: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))

    fun createToken(authentication: Authentication): String {
        val authority = authentication.authorities.stream()
            .map { it.authority }
            .collect(Collectors.joining(","))
        val now = Date().time
        val accessTokenExpiresIn = Date(now + ACCESS_TOKEN_EXPIRE_TIME)
        return Jwts.builder()
            .subject(authentication.name)
            .claim(AUTHORITIES_KEY, authority)
            .expiration(accessTokenExpiresIn)
            .signWith(key)
            .compact()
    }

    // 토큰에 담겨 있는 정보를 받아 유저 정보 리턴
    fun getAuthentication(accessToken: String?): Authentication {
        val claims: Claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(accessToken)
            .payload

        val authorities: Collection<GrantedAuthority> = claims[AUTHORITIES_KEY].toString().split(",")
            .map { role: String? -> SimpleGrantedAuthority(role) }

        val userDetails: UserDetails = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(userDetails, accessToken, authorities)
    }

    fun validateToken(token: String?): Boolean {
        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
            true
        } catch (e: JwtException) {
            false
        }
    }

    companion object {
        private const val DAYS = 1L
        private const val ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * DAYS
        private const val AUTHORITIES_KEY = "role"
    }
}