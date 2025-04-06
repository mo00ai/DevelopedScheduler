# 📅 JPA 기반 일정 관리 백엔드 개선 프로젝트

## 1️⃣ 프로젝트 소개
Spring Boot와 JPA 기반으로 일정 정보를 3 Layer Ahchitecture 구조로 **생성, 조회, 수정, 삭제(CRUD)** 할 수 있는 일정 관리 백엔드 프로젝트입니다. 

## 2️⃣ 기술 스택
- `Language` : Java 17  
- `Framework` : Spring Boot  
- `ORM` : Spring Data JPA  
- `Database` : MySQL  
- `Build Tool` : Gradle  
- `IDE` : IntelliJ IDEA  
- `Architecture` : 3 Layer (Controller - Service - Repository)

## 3️⃣ 프로그램 구조
- HTTP API 명세서
  https://documenter.getpostman.com/view/31225592/2sB2cUBiAA
 
- ERD
  ![image](https://github.com/user-attachments/assets/fbd6614b-cb20-40ec-9c62-143df7f8efbf)



## 4️⃣ 주요 기능

### 일정 CRUD
- 일정 생성 / 조회 / 수정 / 삭제
- 작성자 정보와 연관된 일정 등록
- JPA Auditing 을 사용해서 등록 시간 자동 반영

### 유저 CRUD
- 유저 생성 / 조회 / 수정 / 삭제
- 유저-일정 연관관계 설정

### 회원가입
- 유저 생성 시 비밀번호 입력 필수  
- 이메일, 유저명 등 Validation 적용  

### 로그인 (인증/인가)
- 이메일 + 비밀번호 기반 로그인  
- Cookie/Session을 이용한 인증 처리  
- 인증이 필요한 API는 필터를 통해 접근 제한  

### 예외처리 & Validation
- Bean Validation 적용  
- GlobalExceptionHandler로 발생한 예외처리 통합적으로 대응
- 예외 발생 시 HTTP Status + 상세 에러 메시지 응답

### 비밀번호 암호화 
- BCrypt 라이브러리를 이용한 비밀번호 암호화  
- 암호화된 비밀번호로 로그인 시 사용자 검

## 5️⃣ 실행 방법
1. `SchedulerApplication.java` 실행  
2. Http api 명세서를 참고하여 요청 테스트
