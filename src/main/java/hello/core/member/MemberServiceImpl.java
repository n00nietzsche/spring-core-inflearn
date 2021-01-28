package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
컴포넌트 스캔의 검출 대상이 되기 위해서 `@Component` 애노테이션
 */
@Component
public class MemberServiceImpl implements MemberService {

    /*
    이 부분에서 기존에는 `MemberService`가 추상화에도 의존하고, 구체화에도 의존하고 있었음
     */
    private final MemberRepository memberRepository;

    /*
    생성자를 통해 `memoryRepository`를 설정해주기 때문에,
    이제 `MemberServiceImpl` 내부 구현에서는 구현체가 아닌 인터페이스만 참조한다.
     */
    /*
    `@Autowired`를 통한 의존관계 주입이 가능하다.
    `applicationContext.getBean(MemberRepository.class)` 와 비슷하다.
     */
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        // 받은거로 new 하지말고, new 된 것을 받음
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
