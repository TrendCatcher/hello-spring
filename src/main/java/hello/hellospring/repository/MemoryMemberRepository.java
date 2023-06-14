package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{ // Alt + Enter 키로 인터페이스 메소드 오바라이드 가능

    private static Map<Long, Member> store = new HashMap<>();       // Map 자료구조에 대해 조사, 정리하기
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //id 세팅을 하고
        store.put(member.getId(),member);   //store(맵)에 저장함
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
                .findAny(); //루프를 돌면서 해당하는 것을 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearstore(){ // clear로 싹 한번 비운다.
        store.clear();
    }
}
