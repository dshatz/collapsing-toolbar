plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.kt)
}

android {
    namespace = "com.dshatz.collapsingtoolbar.sample.android"
    compileSdk = 36
    defaultConfig {
        minSdk = 23
        lint.targetSdk = 36
    }
}

dependencies {
    implementation(libs.activitycompose)
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation(project(":demo-shared"))
    implementation("com.google.android.material:material:1.13.0")
}