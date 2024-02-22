plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.todaktodak"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.todaktodak"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding=true
    }
}

dependencies {

    // 혹시 코드 일부가 import되지 않는다면 관련된 모듈을 찾아서 작성하거나 댓글을 남긴다면 확인하고 알려준다
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.activity:activity-ktx:1.7.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    implementation ("com.kakao.sdk:v2-all:2.15.0") // 전체 모듈 설치, 2.11.0 버전부터 지원
    implementation ("com.kakao.sdk:v2-user:2.15.0") // 카카오 로그인

    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // 레트로핏
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    // Gson 컨버터
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // 레트로핏 로깅 인터셉터
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}