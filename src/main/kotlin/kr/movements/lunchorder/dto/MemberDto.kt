package kr.movements.lunchorder.dto

import kr.movements.lunchorder.domain.Member

/**
 * packageName : kr.movements.lunchorder.dto
 * fileName    : MemberDto
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */

data class MemberDto(
    val name: String,
    val email: String,
    val profileImageUrl: String,
    val oauthProviderId: String
) {
    constructor(attributes: Map<String, Any>) : this(
        name = attributes["name"] as String,
        email = attributes["email"] as String,
        profileImageUrl = attributes["picture"] as String,
        oauthProviderId = attributes["sub"] as String,
    )

    fun toEntity(): Member {
        return Member(
            name = name,
            email = email,
            profileImageUrl = profileImageUrl,
            oauthProviderId = oauthProviderId
        )
    }
}
