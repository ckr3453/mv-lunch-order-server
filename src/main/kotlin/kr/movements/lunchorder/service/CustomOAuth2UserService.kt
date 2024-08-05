package kr.movements.lunchorder.service

import kr.movements.lunchorder.dto.MemberDto
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

/**
 * packageName : kr.movements.lunchorder.service
 * fileName    : CustomOAuth2UserService
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */
@Service
class CustomOAuth2UserService(
    private val memberService: MemberService,
): DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oauth2User = super.loadUser(userRequest)
        memberService.saveOrUpdate(MemberDto(oauth2User.attributes))
        return oauth2User
    }
}