package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원 가입
    */
    /*public long join(Member member){
        // Member member1 = result.get();  // 만약에 값을 바로 꺼내고 싶다면
        validateDuplicateMember(member);     // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();

    }*/
    public long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {  //같은 이름이 있는 중복 회원은 없다고 가정
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });     //어떤 값이 만약에 result에 있으면, 단축키로 Ctrl + Alt + M

    }
    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
/*
* repository는 save(), FindByName(), findById()등 넣었다 뻈다하는 느낌이 드는반면
* service는 비즈니스 로직에 가까움 -> ex) 회원가입 로직이상하면 "join 이 쪽 살펴보자"
* 라고 매핑이 됨!
* 따라서 서비스는 비즈니스를 처리하는 것이 Role이다!
* */