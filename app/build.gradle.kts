plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)

    alias(libs.plugins.kotlin.serialization)

}

android {
    namespace = "com.example.gradle"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.gradle"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.play.services.auth)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

//    serializable
    implementation(libs.kotlinx.serialization.json)

    //Icons
    implementation(libs.compose.material.icons.extended)

    //Test
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlin.test)

    //coil
    implementation(libs.coil.compose)


    //SystemUiController
    implementation(libs.accompanist.systemuicontroller)


    //activity
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)


    //hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)


    //Material
    implementation(libs.androidx.compose.material)

    // navigationComponent
    implementation(libs.androidx.navigation.compose)


    // Room DataBase
    implementation (libs.androidx.room.runtime)
    annotationProcessor (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    // Testing
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Room DataBase
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    //google service
    implementation(libs.googleServices)
    implementation(libs.dagger)
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)
    // Firebase
    implementation(libs.firebase.appcheck.safetynet)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)


    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okhttp)

    // Other Libraries

    implementation(libs.kotlinx.datetime)
    implementation(libs.playservicesauth)
    implementation(libs.libphonenumber)

    // Timber for Logging
    implementation(libs.timber)

    // DataStore
    implementation(libs.datastore.preferences)

//    Ktor
    implementation("io.ktor:ktor-client-core:2.3.3") // Core Ktor Client
    implementation("io.ktor:ktor-client-cio:2.3.3") // CIO Engine (for JVM/Android)
    implementation("io.ktor:ktor-client-android:2.3.3") // Android-specific engine
    implementation("io.ktor:ktor-client-serialization:2.3.3") // JSON Serialization
    implementation("io.ktor:ktor-client-content-negotiation:2.3.3") // Content Negotiation
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.3") // JSON Serializer

    implementation("io.ktor:ktor-client-logging:2.3.3") // Logging Plugin
    implementation("ch.qos.logback:logback-classic:1.4.7") // Optional: Logback for better logs
    implementation("io.ktor:ktor-client-auth:2.3.3") // Ktor Authentication

    implementation("androidx.security:security-crypto:1.1.0-alpha06")

//    Coil3
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0")


}