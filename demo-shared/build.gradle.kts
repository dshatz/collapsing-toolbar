
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
    js {
        browser()
    }
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":lib"))
            implementation(compose.foundation)
            implementation(libs.compose.ui)
            implementation(compose.material3)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}
/*compose.application {
    mainClass = "com.dshatz.collapsingtoolbar.AppKt"
}*/
