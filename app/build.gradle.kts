plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}


android {
    namespace = "com.example.anime"
    compileSdk = 34



    defaultConfig {
        applicationId = "com.example.anime"
        minSdk = 26
        targetSdk = 34
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //dependency for RetroFit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //implementation for Moshi
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    // optional - moshi annotation processor for kotlin
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")

    // picasso for loading images in image view
    implementation("com.squareup.picasso:picasso:2.8")

    // needed for background tasks
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Lifecycle components
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    annotationProcessor ("androidx.lifecycle:lifecycle-compiler:2.7.0")



    //for Room components
    implementation ("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

}