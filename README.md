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
![KakaoTalk_20240304_154407861](https://github.com/smhrdMCC/TodakTodak/assets/160091299/0ff37d79-f322-46ad-9b09-1856f013adea)

</br>

## 서비스 흐름도

</br>

## 화면 구성
<details>
<summary><b>달력</b></summary>
<div markdown="1">
![KakaoTalk_20240229_172253329](https://github.com/smhrdMCC/TodakTodak/assets/160091299/521a1d9b-200f-45b1-b072-c906e4dea9ce)

</div>
</details>

<details>
<summary><b>일기 목록</b></summary>
<div markdown="1">
![KakaoTalk_20240229_174440077](https://github.com/smhrdMCC/TodakTodak/assets/160091299/71af4878-d957-471b-94da-15e605a0900a)

</div>
</details>

<details>
<summary><b>차트</b></summary>
<div markdown="1">
![KakaoTalk_20240229_172253423](https://github.com/smhrdMCC/TodakTodak/assets/160091299/afd6cd8d-8634-40fa-954a-78b5aac748f2)

</div>
</details>

<details>
<summary><b>교환일기</b></summary>
<div markdown="1">
![KakaoTalk_20240229_172253639](https://github.com/smhrdMCC/TodakTodak/assets/160091299/0c64f31b-e87d-49a0-90f1-02563fe6e748)

</div>
</details>

</br>

## 트러블 슈팅
### 최준성
<details>
<summary><b>안드로이드에서 어댑터 초기화 오류</b></summary>
<div markdown="1">

---

　🧨 오류 내용

	달력에 클릭이벤트를 추가하면서 어댑터를 초기화 할때 val adapter = CalendarAdapter(dayList, this) 를 사용했는데 추가한 this가 작동하지 않음

　💡 해결 방법
- 클래스의 구현 인터페이스를 선언하지 않아서 발생한 일이었다.
- OnItemListener 인터페이스를 선언하는 것으로 문제를 해결하였다.

```Android
class MainActivity : AppCompatActivity(), OnItemListener 
```

</div>
</details>

<details>
<summary><b>Spring Boot Gson class 오류</b></summary>
<div markdown="1">

---

　🧨 오류 내용

	spring boot 서버에 maven으로 gson을 설치하여 사용하려고 했으나 gson 클래스를 찾을 수 없는 오류가 발생함

　💡 해결 방법
- 안드로이드 연결을 해제하고 서버를 정지시킨 후 gson삭제 -> gson 설치 후 프로젝트 업데이트를 진행함

</div>
</details>

<details>
<summary><b>Retrofit 통신 문제</b></summary>
<div markdown="1">

---

　🧨 오류 내용

	Retrofit객체를 생성하여 본문에 삽입해서 시도했으나 Retrofit으로 요청을 보내고 응답이 오기 전에 나머지 코드가 진행되어버려서 감정 목록이 Null로 진행되는 문제가 발생

　💡 해결 방법
- Retrofit의 응답코드 내부에 응답 이후의 코드를 넣는 방법으로 응답 이후 코드가 진행되게 만들 수 있었다.

```Android
private fun loadEmotion(selectedDate: LocalDate) {
        val call = RetrofitBuilder2.api.getEmotion(datemailVO(searchingFromMonth(selectedDate).toString(), usersingleton.userEmail))
        call.enqueue(object : Callback<ArrayList<emotiondate>> {

            override fun onResponse(
                call: Call<ArrayList<emotiondate>>,
                response: Response<ArrayList<emotiondate>>
            ) {
                if(response.isSuccessful){
                    setMonthView(response.body())
                    emotionsingleton.list = response.body()!!
                } else{
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }
            override fun onFailure(call: Call<ArrayList<emotiondate>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
```

</div>
</details>

<details>
<summary><b>안드로이드, Spring Boot 데이터 전송 문제</b></summary>
<div markdown="1">

---

　🧨 오류 내용

	안드로이드에서 스프링서버로 데이터를 넘기는 과정에서 String타입으로 보낸 데이터를 쿼리문에 넣었을 때 결과를 얻을 수 없었음

　💡 해결 방법
- String 으로 보낸 데이터의 끝에 "가 추가되어서 결과가 나오지 않았다.
-  " 를 제거한 후 실행하자 결과를 얻을 수 있었다.

```Java
String saveDiary = info.replaceAll("\"", "");
		String[] save = saveDiary.split(":");
		String content = save[0];
		String user = save[1];
```

</div>
</details>

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
<details>
<summary><b>Android 1개의 Activity에서 2개의 함수를 동시 실행 시 오류</b></summary>
<div markdown="1">

---

　🧨 오류 내용

	1개의 Activity 내에 2개의 함수를 실행을 시켜야하는 상황이 발생하였는데 첫번째 함수가 실행이 된 후 그 결과를 가지고 2번째 함수가 실행이 되야하는 상황

　💡 해결 방법
- 처음에는 sleep을 사용하여 해결을 하려고 했으나 sleep 시간 만큼 어플이 동작하지 않는 문제가 발생하였다
- 그래서 찾은 방법이 함수와 함수 사이에 Delay를 넣어 해결을 하였으나 멘토와 상담 후 새로운 방법으로 해결하였다
- Lambda 식 표현 방법을 사용하여 함수안에서 함수를 실행하여 해결하였다

```Android
requestChatGptFeedBack(
	prompt = feedback.prompt.toString(),
	onResult = {
	
	    feedback.aiRecommendation = binding.textView4.text.toString()
	    saveChatGptFeedBack(feedback.aiRecommendation.toString() + ":" + DiarySeqSingleton.diarySeq + ":"+ aiEmotion.aiEmo + ":" + date1)
	}
)
```

</div>
</details>

### 윤강석
### 정명훈
### 이상현

</br>

