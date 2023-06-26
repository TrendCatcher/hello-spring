/*
[컴포넌트 스캔과 자동 의존관계 설정]
*/
// :회원 컨트롤러가 회원 서비스와 회원 리포지토리를 사용할 수 있게 의존관계 준비
// 회원 컨트롤러에 의존관계 추가
package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService){   //오류발생, memberService가 스프링 빈으로 등록되어 있지 않음

        this.memberService = memberService;
    }

}
// 생성자에 @Autowired가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아 넣어준다.
// 즉, 컨테이너 내의 memberService를 가져와서 연결해줌
// 이렇게 객체 의좐관계를 외부에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입 이라고 한다.
// 이전에는 개발자가 직접 주입했고, 이번에는 @Autowired에 의해 스프링이 주입해준다.