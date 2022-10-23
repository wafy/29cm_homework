## 구현사항 
 - openjdk 11
 - Springboot 2.6.12
 - db h2
 - guava
 - commons-io
 - querydsl

## 상품데이터
 - 상품데이터는 h2 메모리 디비에 어플리케이션이 로딩되는 시점에 등록됩니다.

## 어플리케이션 아키텍처 
한개의 매소드만 갖는 계층 컨트롤러/서비스 패키지 스타일
* 집합의 포함관계를 강력히 의식한 계층 구조로 패키지를 구성한다.
* 한가지 용도를 암시하는 클래스 이름을 행위와 책음을 제시한다.
* 공개된 메소드를 딱 한개만 갖는 서비스/컨트롤러 클래스를 작성한다.
* command,query의 분리
  * command: 추가 변경 삭제를 담당한다.
  * query: 조회를 담당한다.
  
 - axiom : 공통관심사 패키지
 - controller : 공개된 controller 패키지
 - core : 도메인 로직이 포함된 패키지 
   - cart : 장바구니 패키지
     - command : 장바구니 등록 수정 삭제 담당 패키지
     - query : 장바구니 조회 담당 패키지
   - item : 상품목록 패키지
     - command: 상품의 등록 담당 패키지
     - query: 상품의 조회 담당 패키지
   - order: 주문 패지키
     - command: 주문등록 담당 패키지

## 테스트코드
* BDD 형식으로 테스트 코드를 작성하였습니다.
  * 대상 / 상황 / 행동 으로 구분하여 테스트 코드 작성 
  * 계층형 구조 테스트 코드 
  ![image](https://user-images.githubusercontent.com/310264/197374898-c1a5a52c-a2ff-4f77-bc5f-ede63321656f.png)

## 동시성테스트코드 클래스명
 - 비관적락(for update)으로 구현
 - `kr.co._29cm.homework.core.item.command.ItemUpdaterConcurrentTest`

## 프로젝트 실행 방법 
java -jar _29cm-0.0.1.jar
