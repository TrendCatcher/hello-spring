package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig{
    private DataSource dataSource;
@Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    public MemberService memberService(){       //Bean어노테이션은 스프링 빈에 등록하라는 어노테이션이다.
        return new MemberService(memberRepository()); //spring Bean에 등록되어있는 memberRepository를 memerService에 넣어줌
    }
    @Bean
    public MemberRepository memberRepository(){     //MemberRepository는 인터페이스, MemoryMemberRepository는 구현체

//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }


}
