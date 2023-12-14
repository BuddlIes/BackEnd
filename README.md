<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=9FC700&height=150&section=header" />

# 🌿 Buddle 🌿 </br> 교내 장애인 학우를 위한 봉사 활동 장려 웹 개발  
<img src="https://img.shields.io/badge/IntelliJ%20IDEA%20CE-FF6C37?style=for-the-badge&logo=postman&logoColor=white"/>
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=black"/>
<img src="https://img.shields.io/badge/visual%20studio%20code-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white"/>
<img src="https://img.shields.io/badge/IntelliJ%20IDEA%20CE-0000CD?style=for-the-badge&logo=intellijidea&logoColor=white"/>
<br/><br/>

# 🖥️ 개발 배경 🖥️
**마이크로 자원봉사**에 관한 접근성 향상을 통한 봉사 인식 변화를 도모하고, <br/>
봉사의 생활화를 통한 선한 영향교내 장애인 학우를 위한 **봉사 활동 장려 웹 서비스**의 개발

NFT를 통한 리워드 차등 지급으로 봉사에 대한 참여도를 향상시켜 종합적으로는 교내 장애인 학우를 위한 봉사 활동 장려 웹 개발을 하게 됐습니다.<br/><br/>

# 💚Team BE Members💚
<img width="200px" src="https://github.com/BuddlIes/FrontEnd/assets/100847440/c4dc8df1-9ef1-40dd-a2bc-177ff4f31991"/> | <img width="200px" src="https://github.com/BuddlIes/FrontEnd/assets/100847440/114a2fda-8af6-488a-a968-e0c1c4a80cf9"/> | 
|:-----:|:-----:|
|[소성하](https://github.com/)|[이지은](https://github.com/LeeZEun)|
|백엔드 개발 및 서기 겸임 👩‍💻|백엔드 개발 및 홍보 겸임 🧑‍💻|
</div>
<br/><br/>

<div align="center">
  
# ✍️ 주요 기능 ✍️
</div>

### ☘️ 봉사자 구인 및 도움 요청 글 작성 기능
### ☘️ 봉사 신청 기능 및 댓글 기능
- 봉사 활동 신청 버튼 클릭시 신청 가능
- 추후 socket.io를 이용한 채팅 기능으로 발전시킬 예정
### ☘️ 봉사 활동 현황에 따른 리워드 차등 지급 기능
- NFT를 이용하여 봉사 활동 점수에 따라 리워드를 차등으로 지급 가능
## 
<br/><br/>

<div align="center">

# ✍️ 주요 API ✍️
</div>

### ☘️ 게시물 관리 API
- 게시물 전체 리스트 확인
- 게시물 상세 내용 확인
- 게시물 작성
- 게시물 댓글 전체 리스트 확인
- 게시물 댓글 작성
- 봉사 완료 업로드
### ☘️ 활동 내역 관리
- 완료한 봉사 시간 확인
- 완료한 봉사 횟수 확인
- 완료한 봉사 내역 리스트 확인
### ☘️ 스탬프 관리
### ☘️ NFT 관리
## 
<br/><br/>

<div align="center">
  
# 📜 Rules 📜

</div>

## Navigation
1. [Branch Rules](#branch-rules)
2. [Commit Rules](#commit-rules)<br/><br/>

## Branch Rules
### Main Branch
#### 브랜치 이름 : `main` <br>
소성하 개발자와 이지은 개발자가 작업한 코드를 합치는 브랜치입니다. <br>
해당 브랜치를 이용해 최종 배포가 가능합니다.

### Develop Branch
#### 브랜치 이름 : `ssh` <br>
소성하 개발자가 개인 작업을 진행하는 브랜치입니다. <br>
이 브랜치를 기반으로 기능을 추가하고 버그 없이 안정적으로 배포가 가능한 상태인 경우만 `main`에 병합합니다.

### Develop Branch
#### 브랜치 이름 : `jieun` <br>
이지은 개발자가 개인 작업을 진행하는 브랜치입니다. <br>
이 브랜치를 기반으로 기능을 추가하고 버그 없이 안정적으로 배포가 가능한 상태인 경우만 `main`에 병합합니다.<br/><br/>

## Commit Rules
[참고자료](https://beomseok95.tistory.com/328) <br><br>
일관성 없는 커밋 메시지는 어떤 작업을 했는지 명확하게 알아볼 수 없거나 추후 커밋 히스토리를 파악하는 데 어려움을 줄 수 있습니다. <br>
반대로 네이밍(naming)을 명시적이고 규칙적으로 작성하고 해당 커밋에 대한 내용을 잘 작성한다면 코드를 일일히 분석하지 않아도 해당 커밋의 내용을 알아볼 수 있어요! 👀

1. 제목과 본문을 `빈 행으로 구분` 합니다.
2. 제목을 `50글자 이내` 로 제한합니다.
3. 제목의 첫 글자는 `대문자` 로 작성합니다.
4. 제목의 끝에는 `마침표를 넣지 않습니다`.
5. 제목은 `명령문` 으로 작성하고 `과거형을 사용하지 않습니다`.
6. 본문의 `각 행은 72글자 내로 제한`합니다.
7. 어떻게 보다는 `무엇` 과 `왜` 를 설명합니다.

### Commit Message 구조
헤더는 필수이며, 범위(scope), 본문(body), 바닥글(footer)은 선택사항입니다.

```
<type>(<scope>): <subject>          -- 헤더
<BLANK LINE>
<body>                              -- 본문
<BLANK LINE>
<footer>                            -- 바닥글
```

`type`은 해당 커밋의 성격을 나타내며 아래 중 하나여야 합니다.
```
Feat : 새로운 기능에 대한 커밋
Fix : 버그 수정에 대한 커밋
Build : 빌드 관련 파일 수정에 대한 커밋
Chore : 그 외 자잘한 수정에 대한 커밋
Ci : CI관련 설정 수정에 대한 커밋
Docs : 문서 수정에 대한 커밋
Style : 코드 스타일 혹은 포맷 등에 관한 커밋
Refactor :  코드 리팩토링에 대한 커밋
Test : 테스트 코드 수정에 대한 커밋
```

`body`는 본문으로 헤더로 표현할 수 없는 상세한 내용을 적습니다.
헤더로 표현이 가능하다면 생략 가능합니다.
`footer`는 바닥글로 어떤 이슈에서 왔는지 같은 참조 정보들을 추가하는 용도로 사용합니다.
예를 들어 특정 이슈를 참조하려면 close #1 과 같이 추가하면 됩니다.
close는 이슈를 참조하면서 main 브랜치로 푸시될 때 이슈를 닫게 됩니다.

---



<img src="https://capsule-render.vercel.app/api?type=waving&color=9FC700&height=150&section=footer" />
