package kr.movements.lunchorder.repository

import kr.movements.lunchorder.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

/**
 * packageName : kr.movements.lunchorder.repository
 * fileName    : MemberRepository
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */
interface MemberRepository: JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?
}