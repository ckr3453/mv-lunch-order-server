package kr.movements.lunchorder.domain

import jakarta.persistence.*

/**
 * packageName : kr.movements.lunchorder.domain
 * fileName    : Member
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */

@Entity
@Table(name = "member")
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long? = null,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false, unique = true)
    val oauthProviderId: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: MemberRole = MemberRole.ROLE_USER,

    @Column(nullable = false)
    var name: String,

    @Column
    var profileImageUrl: String,
) {
    fun updateProperties(
        name: String,
        profileImageUrl: String,
    ) {
        this.name = name
        this.profileImageUrl = profileImageUrl
    }

    fun updateRole(role: MemberRole) {
        this.role = role
    }
}

enum class MemberRole {
    ROLE_USER, ROLE_MANAGER, ROLE_ADMIN
}
