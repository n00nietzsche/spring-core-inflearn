package hello.core.member;

public interface MemberService {
    /*
    요구사항에 따라
     */
    void join(Member member);
    Member findMEmber(Long memberId);
}
