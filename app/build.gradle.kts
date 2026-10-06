plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.retroreadme"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.retroreadme"
        minSdk = 26          // Nova runs Android 13; 26 keeps adaptive icons and older handhelds
        targetSdk = 35
        versionCode = 2
        versionName = "0.2.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            // Personal sideload build: sign release with the debug key so it installs without extra setup.
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures { compose = true }
    // The web export (src/test) loads guide content on the JVM; stubbed Android calls return defaults.
    testOptions { unitTests.isReturnDefaultValues = true }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.12.01")
    implementation(composeBom)
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")

    // Test-only: runs the web export (see src/test/.../web/WebExport.kt). Not part of the app.
    testImplementation("junit:junit:4.13.2")
}
