plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android { namespace = "com.righttofitness.ai"; compileSdk = 36
    defaultConfig { applicationId = "com.righttofitness.ai"; minSdk = 29; targetSdk = 36; versionCode = 1; versionName = "1.0.0"; testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" }
    buildTypes { release { isMinifyEnabled = true; isShrinkResources = true; proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro") } }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlin { compilerOptions { jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17) } }
    buildFeatures { compose = true; buildConfig = true }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2026.06.00")
    implementation(composeBom); androidTestImplementation(composeBom)
    implementation("androidx.core:core-ktx:1.17.0"); implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2"); implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.compose.ui:ui"); implementation("androidx.compose.ui:ui-graphics"); implementation("androidx.compose.ui:ui-tooling-preview"); implementation("androidx.compose.material3:material3"); debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.navigation:navigation-compose:2.9.3"); implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("com.google.dagger:hilt-android:2.57"); ksp("com.google.dagger:hilt-compiler:2.57")
    implementation("androidx.room:room-runtime:2.7.2"); implementation("androidx.room:room-ktx:2.7.2"); ksp("androidx.room:room-compiler:2.7.2")
    implementation("androidx.datastore:datastore-preferences:1.1.7")
    implementation("com.squareup.retrofit2:retrofit:3.0.0"); implementation("com.squareup.retrofit2:converter-kotlinx-serialization:3.0.0"); implementation("com.squareup.okhttp3:okhttp:5.1.0"); implementation("com.squareup.okhttp3:logging-interceptor:5.1.0")
    implementation("io.coil-kt:coil-compose:2.7.0"); implementation("com.airbnb.android:lottie-compose:6.6.7")
    implementation("androidx.security:security-crypto:1.1.0")
    implementation("androidx.biometric:biometric-ktx:1.4.0-alpha04")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    testImplementation("junit:junit:4.13.2")
}
