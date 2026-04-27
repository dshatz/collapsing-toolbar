plugins {
    alias(libs.plugins.mp)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.kt)
}

kotlin {
    jvmToolchain(21)
    js {
        browser()
        outputModuleName = "webapp"
        binaries.executable()
    }
    wasmJs {
        browser()
        outputModuleName = "webapp"
        binaries.executable()
    }

    sourceSets {
        webMain.dependencies {
            implementation(project(":demo-shared"))
            implementation(compose.ui)
        }
    }
}