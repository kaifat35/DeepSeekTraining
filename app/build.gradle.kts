plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.deepseektraining"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.deepseektraining"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.androidx.navigation.compose)
    implementation (libs.ui)
    implementation (libs.androidx.material)
    implementation (libs.ui.tooling.preview)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.activity.compose.v182)
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("org.mockito:mockito-core:5.5.0")
    testImplementation (libs.mockito.kotlin)
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.1")

    // Retrofit для работы с API
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coil для загрузки изображений
    implementation ("io.coil-kt:coil-compose:2.7.0")
    
    //Hilt для работы с API
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    ksp ("com.google.dagger:hilt-android-compiler:2.51.1")

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp("androidx.room:room-compiler:2.6.1")
    // DataStore
    implementation ("androidx.datastore:datastore-preferences:1.1.4")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")

    // Для обработки метаданных
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.7.0")

    //WorkManager
    //implementation ("androidx.work:work-runtime-ktx:2.10.0")


}