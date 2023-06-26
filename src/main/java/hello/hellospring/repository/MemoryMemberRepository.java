package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MemoryMemberRepository implements MemberRepository{ // Alt + Enter 키로 인터페이스 메소드 오바라이드 가능

    private static Map<Long, Member> store = new HashMap<>();       // store.(확장자)을 통해 HashMap 자료구조 접근 가능
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //id 세팅을 하고
        store.put(member.getId(),member);   //store(hash map)에 저장함, 자료형은 <Long, Member>였음
        return member;  //결과 반환

    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //루프를 돌면서 해당하는 것을 반환(추가조사 필요)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearstore(){ // clear로 싹 한번 비운다.
        store.clear();
    }
}
