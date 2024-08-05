package kr.movements.lunchorder.service

import kr.movements.lunchorder.domain.Member
import kr.movements.lunchorder.dto.MemberDto
import kr.movements.lunchorder.repository.MemberRepository
import org.springframework.stereotype.Service

/**
 * packageName : kr.movements.lunchorder.service
 * fileName    : MemberRepository
 * author      : ckr
 * date        : 24. 8. 2.
 * description :
 */

@Service
class MemberService (
    private val memberRepository: MemberRepository
) {
    fun saveOrUpdate(memberDto: MemberDto): Member {
        val member = memberRepository.findByEmail(memberDto.email)?.apply {
            this.updateProperties(
                name = memberDto.name,
                profileImageUrl = memberDto.profileImageUrl
            )
        } ?: memberDto.toEntity()
        return memberRepository.save(member)
    }
}