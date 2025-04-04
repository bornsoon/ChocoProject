<div align="center">

# 🐾🐶My Cute Pet😸🐾
##### 반려동물과의 소중한 추억을 공유할 수 있는 플랫폼을 제공하는 웹 서비스
###### 🌟 반려동물 가족 간의 유익한 교류 및 커뮤니티 형성
###### 🌟 반려동물의 건강과 행복을 위한 정보 제공
<br>
</div>
<br>
<h2>📜 목차</h2>
01. <a href=#1>프로젝트 소개</a><br>
02. <a href=#2>ERD</a><br>
03. <a href=#3>Architecture</a><br>
04. <a href=#4>담당 역할</a><br>
05. <a href=#5>상세 구현</a><br>
10. <a href=#6>문제점 및 해결 방안</a><br>
06. <a href=#7>회고</a><br>
<br><br>

<h2 id=1>📜 About</h2>

##### 🗓️ 작업기간 : 2024.11.13 ~ 2024.11.27 (2주간)
##### 👩🏻‍👧‍👦 참여인원 : 4인
##### 🔧 기술스택
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;🖥️ 개발 환경
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <img src="https://img.shields.io/badge/Spring Tools 4-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/OracleDB 21c Express-FAA61A?style=for-the-badge&logo=oracle&logoColor=white">
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;🔧 도구 및 버전 관리
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">
<br>
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📝 언어
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white"> <img src="https://img.shields.io/badge/HTML-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/CSS-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"><br>
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;🌱 프레임워크 및 라이브러리
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/MyBatis-4479A1?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"> <img src="https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jQuery&logoColor=white"/>

<br>

<h2 id=2>✏️ ERD</h2>

![ERD](https://github.com/user-attachments/assets/29e2beab-69be-4d92-a00e-7d527aeb244e)


<h2 id=3>✏️ Architecture</h2>

![Architecture](https://github.com/user-attachments/assets/32964052-66cb-46af-8ed0-58a6ac45a15e)

<br>
<h2 id=5>📌 담당 역할</h2>

- **`  프로젝트 개발 총괄   `**
- **`  데이터베이스 구성   `** 
- **`  Git 관리 총괄  `**
- **`  게시판  `**
  - 게시글 등록, 수정 및 삭제
  - 게시글 최신순, 인기순 정렬
  - 댓글 등록, 수정 및 삭제
  - 게시글 좋아요
- **`  마이페이지 '나의 활동'  `**
  - 나의 게시글 조회하기
  - '좋아요'한 게시글 조회하기

<br>

#### **📌 `  게시글 & 댓글 & 좋아요  `**
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=https://github.com/user-attachments/assets/3363bc40-017c-4baa-9477-7a496fb6481f width=700>
<br><br>

#### **📌 `  마이페이지 '나의 활동'  `**
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=https://github.com/user-attachments/assets/72bc45ee-423e-4edf-a5b8-7ba10025493a width=700>

<br>
<h2 id=4>📌 상세 구현</h2>

**`회원가입 & 로그인`**

https://github.com/user-attachments/assets/06a10377-3bae-403c-af34-001eef4a3a6b

<br>

**`게시글 & 댓글 & 좋아요`**

https://github.com/user-attachments/assets/68ec149f-0cce-4e6c-bd54-1807029046b1

<br>

**`나의 활동 & 건강 계산기`**

https://github.com/user-attachments/assets/ac163ddf-183b-4da6-87ef-4f0c64fd1973

<br><br>
<h2 id=10>🔍 문제점 및 해결 방안💡</h2>

### 1️⃣ 게시글, 댓글, 좋아요 테이블 설계 문제 해결
#### 🔍 문제점
- ##### 게시글과 댓글이 서로 다른 테이블에서 관리되면서 좋아요 데이터를 따로 저장해야 하는 구조가 되어 `테이블이 분리됨`.
- ##### 이에 따라 좋아요 기능을 위한 별도 테이블이 필요했고, 이를 `관리하는 로직도 복잡`해짐.
- ##### 게시글과 댓글에 대한 `좋아요 기능을 통합적으로 관리`할 방법이 필요했음.
#### 💡 해결 방안
- ##### `게시글과 댓글의 기본 키를 동일한 시퀀스`에서 생성하도록 변경하여 `ID가 충돌하지 않도록 통합`.
- ##### 좋아요 데이터를 별도 테이블이 아닌 `하나의 테이블에서 관리`할 수 있도록 구조 개선.
- ##### 테이블 설계를 최적화하여 데이터베이스 구조를 간결하게 유지하고 `관리의 복잡성을 줄임`.
<br>


<h2 id=7>💬 회고</h2>

###### *이번 프로젝트는 두 번째 팀 프로젝트이자, Spring Boot를 배우고 직접 적용한 첫 경험이었습니다.<br> 이를 통해 Spring Framework의 전반적인 구조와 MVC 패턴을 깊이 이해할 수 있는 좋은 기회가 되었습니다.*
###### *특히, 팀원들 중 저를 제외한 모두가 웹 개발이 처음이었기 때문에 자연스럽게 많은 역할을 맡게 되었고, 그 과정에서 더욱 많은 것을 배울 수 있었습니다.*
###### *또한, 게시글, 댓글, 좋아요 기능을 구현하면서 데이터베이스에서 다양한 방식으로 조인을 활용하여 데이터를 조회하는 법을 익힐 수 있었습니다.*
