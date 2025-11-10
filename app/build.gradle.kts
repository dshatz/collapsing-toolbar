plugins {
    alias(libs.plugins.mp)
    alias(libs.plugins.compose.kt)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.app)
}

repositories {
    google()
    mavenCentral()
}

android {
    namespace = "com.dshatz.collapsingtoolbar.sample"
    compileSdk = 35
    defaultConfig {
        minSdk = 21
        targetSdk = 35
    }
}

kotlin {
    jvmToolchain(21)
    jvm()
    androidTarget()
    sourceSets {
        commonMain.dependencies {
            implementation(project(":lib"))
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(compose.desktop.common)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
        androidMain.dependencies {
            implementation(libs.activitycompose)
            implementation("androidx.appcompat:appcompat:1.7.1")
        }
    }
}
/*compose.application {
    mainClass = "com.dshatz.collapsingtoolbar.AppKt"
}*/
