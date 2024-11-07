plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.chandra.practice.pointofsaleapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.chandra.practice.pointofsaleapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    /*signingConfigs {
        release {
            storeFile file ("path/to/your/keystore")
            storePassword "your-store-password"
            keyAlias "your-key-alias"
            keyPassword "your-key-password"
        }
    }*/

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt") ,
                    "proguard-rules.pro"
                         )
           // signingConfig signingConfigs.release

        }
    }
    buildFeatures {
        buildConfig = true
    }

    viewBinding.enable = true
    dataBinding.enable = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    //Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    //gson
    implementation(libs.gson)
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    // Room components
    implementation (libs.androidx.room.runtime)
    kapt ("androidx.room:room-compiler:2.6.1") // For Kotlin use kapt
    // Lifecycle components
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Lifecycle dependencies (for lifecycleScope)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Coroutine integration with Room
    implementation(libs.androidx.room.ktx)
    // Room Testing
    testImplementation(libs.androidx.room.testing)


}