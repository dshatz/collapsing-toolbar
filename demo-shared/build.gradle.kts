
plugins {
    alias(libs.plugins.mp)
    alias(libs.plugins.compose.kt)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.lib)
}

kotlin {
    jvmToolchain(21)
    jvm()
    android {
        namespace = "com.dshatz.collapsingtoolbar.sample"
        compileSdk = 36
        minSdk = 23
    }

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
    }
}
/*compose.application {
    mainClass = "com.dshatz.collapsingtoolbar.AppKt"
}*/
