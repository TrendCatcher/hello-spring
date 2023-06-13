# hello-spring
## Description 
본 프로젝트는 김영한 강사님의 스프링을 수강하고 정리한 내용입니다.

## 🔍 진행 방식

- 김영한 전 우아한 형제 CTO님의 스프링 영상 시청
- 노션에 정리, IDE(IntelliJ)내에서 실습하며 주석달기
- github에 푸시하고 프로젝트 관리 (README.md파일 및 리팩토링)

## 폴더 구조
```sh
hello-spring
├─ src 
   │  
   ├─ main    //chat client의 인스턴스를 구조화 함
   │    │
        └─  java  
              └─ (생략) hello.hellospring
                              └─ controller
                                      └─ HelloController.java // JSON방식으로 데이터 송수신
                              └─ domain
                                  └─ Member.java
                              └─ repository //회원 아이디를 저장, 찾는 기능
                                    └─ MemberRepository(Interface)
                                    └─ MemoryMemberRepository
                              └─ service   
                                    └─ MemberService  // 회원가입 && 전체회원 조회
   │    └─  resources
   └─ test    // test case에 관한 파일     
    │
    └─ ClientGUI.java   // chat client를 위한 UI 설계
    │
    │
    └─ SimpleChatClientFrame.java // 서버 UI와 서버 인스턴스의 생성
```
## 📮 진행 내용

- 비즈니스 요구사항
  - 데이터: 회원 ID, 이름
  - 기능: 회원 등록, 조회
  - 아직 데이터 저장소가 선정되지 않음 (**가상의 시나리오**)

### *일반적인 Web 어플리케이션의 구조*
- ![img.png](img.png)
    - 컨트롤러: 웹 MVC와 컨트롤러의 역할
    - 서비스: 핵심 비즈니스 로직 구현로직들이 들어감
        - 회원은 중복되면 안된다등등…
    - 리포지토리(repository): 데이터베이스 접근, 도메인 객체를 DB에 저장하고 관리
    - 도메인: 비즈니스 도메인에 객체(예> 회원, 주문, 쿠폰) 등등 주로 데이터베이스에 저장하고 관리됨

### *클래스 의존 관계*
![img_1.png](img_1.png)
- Documentation
  - 아직 데이터 저장소가 선정되지 않아서, 우선 인터페이스로 구현 클래스를 변경할 수 있도록 설계
  - 데이터 저장소는 RDB, NoSQL 등등 다양한 저장소를 고민 중으로 <span style="color:blue">**가정**</span>
  - 개발을 진행하기 위해서는 초기 개발 단계에서는 구현체로 가벼운 메모리 기반의 데이터 장소 사용

## 🚨 회원 repository TC 작성

개발한 기능을 실행해서 테스트 할 때,
1. main method를 통해서 실행
2. 웹 어플리케이션의 컨트롤러를 통해서 실행
   할 수 있으나 다음과 같은 `**단점**`이 있다. 
   1. 오래 걸린다.
   2. 반복 실행하기 어렵다
   3. 여러 테스트를 한번에 실행하기 어렵다.

자바는 이러한 문제를 해결하기 위해 <span sttle="color:green">**JUnit**</span>이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.


### Observer pattern
클래스 다이어그램을 통해서 객체의 상태변화를 관찰하는 Obervers의 동작원리를 이해
실시간 통신이 어려운 기존의 polling 방식에서 벗어나 update()함수를 사용하는 방식 적용


```

```

---

## 🚀phase 2 기능 요구 사항

1.
2.
3.

---

## 🎯 알면 유용한 IntelliJ 단축키

-

---
