
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.mp)
//    alias(libs.plugins.compose.kt)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.lib)
    alias(libs.plugins.publish)
}

group = "com.dshatz"
version = project.findProperty("version") as? String ?: "0.1.0-SNAPSHOT1"

repositories {
    google()
    mavenCentral()
}

android {
    namespace = "com.dshatz.collapsingtoolbar"
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
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.runtime)
            implementation("androidx.annotation:annotation:1.9.1")
        }
        jvmTest.dependencies {
            implementation(compose.desktop.uiTestJUnit4)
            implementation(compose.desktop.currentOs)
        }
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates(project.group.toString(), "collapsing-toolbar", project.version.toString())
    pom {
        name = "Collapsing Toolbar Compose"
        description = "Collapsing Toolbar implementation for Kotlin Compose"
        inceptionYear = "2025"
        url = "https://github.com/dshatz/collapsing-toolbar/"
        licenses {
            license {
                name = "GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007"
                url = "https://github.com/dshatz/collapsing-toolbar/blob/main/LICENSE"
            }
        }
        developers {
            developer {
                id = "dshatz"
                name = "Daniels Šatcs"
                email = "dev@dshatz.com"
            }
        }
        scm {
            url = "https://github.com/dshatz/collapsing-toolbar"
            connection = "git@github.com:dshatz/collapsing-toolbar.git"
        }
    }
}