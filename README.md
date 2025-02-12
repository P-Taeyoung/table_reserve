# 개요
테이블 예약 서비스 구현

Use : Spring, Jpa, MariaDB, Docker 

Goal : 식당이나 점포를 이용하기 전에 미리 예약을 하여 편하게 식당/점포를 이용할 수 있는 서비스 개발

## 회원
### 공통
- 이메일을 통해서 인증번호를 통한 회원가입
- 로그인시 회원 권한이 구분된 Jwt 토큰 발행

## 서비스
### 매장 등록 (점장)
- 매장 등록, 수정, 삭제

### 예약 (고객) 
- 매장 검색 후 상세정보 조회
- 30분 단위로 메장예약 
- 현재 시간 이후로만 예약 가능
- 이미 해당 타임에 예약 정원 마감인 경우에 예약 불가능
- 예약 목록 조회 후 예약테이블 사용
- 확인 후 예약된 테이블 사용 진행
    - 10분 전까지 이용가능 10분지나면 예약 무효 처리 (이때 초단위는 생략)
    - 예약 상태 (예약 이용 전, 예약 무효, 예약 사용완료)
### 리뷰 (고객, 점장)
 - 예약 사용완료 이후에 리뷰 작성 가능
 - 사용완료된 예약내역 조회 후 리뷰 작성
 - 매장 이용 고객만 리뷰 작성 가능
 - 수정은 리뷰 작성자만 가능
 - 삭제는 리뷰 작성자와 매장 점주 둘다 가능



