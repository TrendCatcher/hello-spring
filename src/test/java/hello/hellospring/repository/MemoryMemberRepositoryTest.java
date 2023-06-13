package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

     MemoryMemberRepository repository = new MemoryMemberRepository();

     @AfterEach
     public void afterEach(){       //콜백함수, save()킅나고 findByName(), findByName()이 끝나고 findAll()
        repository.clearstore();    // 한번 실행 될때 마다 클리어 함
     }
     @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));
         //Assertions.assertEquals(member, result);
         //Assertions.assertThat(member).isEqualTo(result);   // Assertions 선택후 alt + Enter치면 자동 import후 아래로 바뀜
         assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName(){   //spring1과2가 회원가입함
         Member member1 = new Member();
         member1.setName("spring1");
         repository.save(member1);

         Member member2 = new Member();
         member2.setName("spring");
         repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
         Member member1 = new Member();
         member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
