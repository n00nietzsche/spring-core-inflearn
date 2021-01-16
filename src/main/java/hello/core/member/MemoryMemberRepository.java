package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    /*
    사실 메모리 DB는 동시에 여러군데에서 접근할 수 있기 때문에
    Concurrent Hash Map을 쓰는것이 맞긴 하다..
    그런데 여기서는 단순 예제 용도기 때문에...
    */
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}

