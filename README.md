# Readme 작성중
# 📔 토닥토닥 : 팀 MCC

><strong>DistilBERT와 ChatGPT를 활용한 일기 감정 분석 및 AI 친구 서비스</strong>
* 스마트 인재 개발원 인공지능 융합 서비스 과정의 마지막 프로젝트로 기업의 멘토링을 받아 개발하였습니다.
* 스트레스, 불안, 우울 등의 부정적 감정에 힘들어 하는 사람들이 감정을 해소하고 다스릴 수 있도록 감정을 기록하고 관리에 도움을 주는 도구로서 제작하였습니다.
* 다른 이에게 자신의 감정을 표현하고 소통하는 데에 도움이 되도록 합니다.

</br>

## 프로젝트 기간
* 계획 / 분석 / 설계 : 24.01.08 ~ 24.02.12
* 구현 : 24.02.13 ~ 24.03.06

</br>

## 사용 기술
#### `Back-end`
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)
#### `IDE`
![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white)
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)
#### `Server`
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![Flask](https://img.shields.io/badge/flask-%23000.svg?style=for-the-badge&logo=flask&logoColor=white)
#### `DB`
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)
#### `Library & API`
![ChatGPT](https://img.shields.io/badge/chatGPT-74aa9c?style=for-the-badge&logo=openai&logoColor=white)
<img src="https://img.shields.io/badge/Spring Data JPA-bcae79?style=for-the-badge&logo=&logoColor=white"/>
#### `Etc`
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

</br>

## 시스템 아키텍처

</br>

## ERD

</br>

## 서비스 흐름도

</br>

## 화면 구성
<details>
<summary><b>화면 1</b></summary>
<div markdown="1">
</div>
</details>

<details>
<summary><b>화면 2</b></summary>
<div markdown="1">
</div>
</details>

<details>
<summary><b>화면 3</b></summary>
<div markdown="1">
</div>
</details>

</br>

## 트러블 슈팅
### 최준성
### 김영준
<details>
<summary><b>안드로이드에서 SpringBoot 서버로 데이터 전송 오류</b></summary>
<div markdown="1">

---

　🧨 오류 내용

	안드로이드에서 SpringBoot 서버로 데이터가 전송을 했으나 서버에서 로그가 안찍히는 오류

　💡 해결 방법
- 안드로이드 Retrofit 기능에 BaseURL에 아이피 주소를 localhost:port 작성하지 않고 10.0.0.2를 입력 후 해결
- 10.0.0.2는 안드로이드 에뮬레이터에서 127.0.0.1 즉 루프백 주소

```Android
val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8100/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
```

</div>
</details>

<details>
<summary><b>ChatGPT API 연동 시 API-KEY 값을 읽지 못하는 오류</b></summary>
<div markdown="1">

---

　🧨 오류 내용

	공식 문서에서는 API-KEY를 환경변수를 이용하여 가져왔으나 실제로 적용을 해보니 불러오지 못함

　💡 해결 방법
- open 함수를 사용하여 파일을 읽어 들인 후 변수에 저장하여 해결

```Python
with open('./gpt_api_key.txt', 'r') as f:
    api_key = f.read().strip()  # 시작/끝에 있는 모든 공백 제거
```

</div>
</details>
<details>
<summary><b>SpringBoot 서버에서 DB의 테이블, 컬럼에 접근 못하는 오류</b></summary>
<div markdown="1">

---

　🧨 오류 내용

	org.hibernate.exception.SQLGrammarException: could not extract ResultSet

　💡 해결 방법
- 해당 Entity java 파일들에 @Table(name = "tb_user"), @Column(name = "user_email") 어노테이션을 추가하여 해결

```Java
@Table(name = "tb_user")
public class User {

	@Id
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "user_nick")
	private String userNick;
}
```

</div>
</details>

### 윤강석
### 정명훈
### 이상현

</br>

