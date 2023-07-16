# hello-spring

## 🔍 진행 방식 (Live Document)

- 김영한 전 우아한 형제 CTO님의 스프링입문 영상 시청
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
                                    └─ MemberService  // 회원가입(join()) && 전체회원 조회
   │    └─  resources
   └─ test    // test case에 관한 파일     
    │
    └─ repository  
    │     └─MemoryMemberRepositoryTest  // member 회원가입, 조회 테스팅
    │
    └─ service 
          └─MemberServiceTest // 회원가입, 중복제거 테스팅
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

자바는 이러한 문제를 해결하기 위해 <span sttle="color:green"> **JUnit** </span>이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.


## 디자인 패턴






## 🚀 더 조사하고 정리할 것들 (Live Document🚩)
- <src/main>
- repository/MemoryMemberRepository.java
    <자료형>
  - Hashmap
    - 데이터 검색 및 조회: 키-쌍을 저장하는 자료구조로, 특정 키를 사용하여 값을 검색하고 조회하는 데 효과적이다.  
  - List
  <그 외>
  - sequence
  - Optional
    - [chatgpt ref]`Optional` is class used to represent an object that may or may not exist.
    - it provides a way to handle situations where a value could be null, allowing you to avoid `null checks` and `NullPointerExceptions`
    <Related methods>
    - `OfNullable()`
      - Returns an Optional describing the given value, if non-null, otherwise returns an empty Optional.
      - **Params(매개변수)**:
        value – the possibly-null value to describe
      - **Returns(리턴형)**:
        an Optional with a present value if the specified value is non-null, otherwise an empty Optional
    - `isPresent()`
      - If a value is present, performs the given action with the value, otherwise does nothing.
      - **Params(매개변수)**:
        action – the action to be performed, if a value is present
      - Throws NullPointerException – if value is present and the given action is null
      
 

  - 자바코드로 스프링 빈 설정시 유리한점
    - 상황에 따라 구현 클래스를 변경해야할 때 설정을 통해 변경(**가장 중요한 이유**)
  
      - 코드분석 
```java
    return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
```
- Stream API: 다량의 처리작업을 돕고자 자바8에 추가됨, 제공하는 핵심 추상개념 2가지는 다음과 같다. 
  1. `스트림`은 데이터 원소의 유한 혹은 무한 시퀸스를 뜻함.
  2. `스트림 파이프 라인`은 이 원소들로 수행하는 연산 단계를 포함.
     - 소스 스트림에서 시작해 종단 연산(terminal operation)으로 끝나며, 그 사이 하나 이상의 중간연산(intermediate operation)이 존재할 수도 있다. 
  - Stream안의 데이터 원소들은 객체 참조나 기본 타입 값(int, dougle, long)이다.  

1. store.values() : retrieves the collection of 'Member' objects from 'store' variable
2. .stream(): converts the collection of 'Member' objects into a stream
              Streams provide a way to perform operations on a sequence of elements.
3. .filter(~): applies a filter to the stream, only allowing 'Member' objects whose name matches
                the provided 'name' parameter to pass through.
4. findAny(): returns an 'Optional <Member>' object containing an arbitrary 'Member' object
              from the filtered stream, or an empty 'Optional' if no match is found.
              the "findAny()' operation is non-deterministic and doesn't guarantee a specific element from the stream. 
- <src/test>
  - @AfterEach: 해당 어노테이션 안의 메소드는 @Test 어노테이션이 끝나면 반드시 실행이 됨.
    - MemoryMemberReposotory.java안의 @AfterEach는 테스트 한번 실행시 DB를 비우는 기능으로 활용됨

- Section 5 [회원 관리 예제 웹 MVC개발 - homescreen]
  - 정적콘텐츠 입력시 localhost:8080 요청이 오면...
  

  1. 먼저 Controller(Homecontroller)를 뒤진다.
  2. 없으면 정적리소스를 호출한다. 
  - 여기서는 HomeController를 뒤지고 매핑된 url을 찾아 띄운다.

###  Section 5 [회원 관리 예제 웹 MVC개발 - memebr register ]
  - Get vs Post 
    - Get: url던지기
    - Post: 데이터를 폼에 넣어서 전달 
### Section 6 [Pure JDBC]
> Spring를 사용하는 이유
- 인터페이스를 두고 구현체를 바꿔끼기 가능! (다형성)
- DI(Dependency Injection)가 스프링의 컨테이너를 통해 구현됨
- OOP(Opened-Closed Principle)
  - application 동작에 필요한 코드 변경은 안 해도 됨!

### Section 6 [Integration Test]
- @SpringBootTest
  - 스프링 컨테이너와 함꼐 테스트 한다. 
- @transactional
  - test 코드에 넣으면 transaction을 먼저 실행하고, DB에 데이터를 넣었다면,  테스트 종료시 데이터를 롤백함(반영 X).
  - @beforeeach, @aftereach 없이도 `테스트 반복` 가능!
- 가급적이면 순수 단위 테스트로 진행하자!
    - SpringContainer를 사용한 테스트는 `지양`!

---

## 🎯 알면 유용한 IntelliJ 단축키 및 꿀팁
> 클래스 검색, 설정 및 주석 관련

- `Alt + Enter` : 필요한 기능 수정가능 
- `Ctrl + Shift + Enter` : 자동 완성 기능
- 블록 주석(/**/)과 한줄 주석(//) 색깔 변경 가능!
  - 나의 경우 블록 주석만 초록색으로 변경하여 코드 설명을 위한 더 가독성있는 요소를 고려함
- `Ctrl + Shift + /`: 주석처리(블록/**/)
- `Ctrl + /`: 주석처리 (//)
- `Alt + Insert`: `Generate`기능으로 생성자(Constructor)등을 삽입할 수 있다. 
- `Ctrl + 클릭`: 해당 클래스로 이동
- `Ctrl + Alt + N`: 파일(클래스) 생성
- `Ctrl + N`: 파일(클래스) 컴색
- `Ctrl + 마우스 클릭`: 해당 메소드혹은 변수로 들어감
> 테스팅 관련

- Ctrl + Shift + T: 테스트 코드 자동생성 (테스트를 하고자 하는 클래스 이름에 입력)

---

## 💫 병행할 토이 프로젝트 주제
- 심리케어 웹 서비스

**[possible services]**
  1. 현재 심리상태 진단
     - 진단 토대로 가장 가까운 오프라인 상담소 혹은 병원 보여주기


  2. 오늘 할려는 업무와 관계된 글귀 띄우기
     - 업무집중도를 향상

**[Techniques]**
  - CRUD 구현

## ❗ Debugging and other Reference
- [8080포트 사용중 에러](https://pingfanzhilu.tistory.com/entry/Spring-boot-Web-server-failed-to-start-Port-8080-was-already-in-use-%EC%97%90%EB%9F%AC-%ED%95%B4%EA%B2%B0-%EB%B0%A9%EB%B2%95)
- [h2.bat 실행 오류](https://velog.io/@godqhrals/%EC%9C%88%EB%8F%84%EC%9A%B0-H2-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-%EC%8B%A4%ED%96%89-%EC%B4%88%EB%B0%98%EC%97%90-h2.bat-%EC%8B%A4%ED%96%89-%EC%98%A4%EB%A5%98)
  - cmd 관리자 모드 실행 후 bin 폴더에서 `"h2.bat"` 입력 
- [cmd에서 특정 파일 이름 찾기]: dir /s `filename`
- [javax.sql.DataSource import 안 됨 에러] 
  - 캐시를 비우고 재실행. 상단 메뉴바에서 `File` > `Invalidate Caches` /` Restart`를 선택하고 IDE가 재실행되기를 기다리면 해결!