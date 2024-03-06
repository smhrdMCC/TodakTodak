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
<summary><b>Failed to connect to service endpoint</b></summary>
<div markdown="1">

---

　🧨 오류 내용

	com.amazonaws.SdkClientException: Failed to connect to service endpoint:
	Caused by: java.net.SocketTimeoutException: connect timed out

　💡 해결 방법
- spring-cloud-starter-aws 의존성 주입시 로컬환경은 AWS환경이 아니기때문에 발생한다.
- 아래 구문을 SpringBootApplication에 적용하였음.

```java
@SpringBootApplication(
      exclude = {
              org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
              org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
              org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
      }
 )
```

</div>
</details>

### 윤강석
### 정명훈
### 이상현

</br>

