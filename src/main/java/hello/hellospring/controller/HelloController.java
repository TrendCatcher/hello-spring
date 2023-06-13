package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }




    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; //"hello spring"
    }
// [기본] @ResponseBody 후 객체로 반환 >> JSON방식
    /*
    <동작방식>
    객체로 넘어오면 기본 JSON방식으로 data를 만들어서 반환을 한다.
    @ResponseBody내 반환형이
    객체일 때: HttpMessageConverter가 동작 (JSON방식)
    문자열일 때: StringHttpMessageConverter가 동작

    *
    * */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloapi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    // Getter와 Setter를 항상 Controller의 디폴트로 지정한다.

    static class Hello{     // Hello 클래스를 static으로 선언
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
/*
* @GetMapping과 @ResponseBody가 spring framework에서 쓰이는 역할
        <@GetMapping>
*   - @GetMapping은 HTTP GET 요청을 매핑하기 위해 사용되는 스프링MVC Controller내의 세부 핸들러 중 하나이다.
*   - We can indicate that the method should be invoked when a GET request is made to the specific URL
*
* <@ResponseBody>
    - "ResponseBody" annotation is uesed to indicate that the return valuse od a handle r method should be a
        serialized and included a s the response body
    - converts the return values to the apropriate format, such as JSON or XML
*  */

