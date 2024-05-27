import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

val API_KEY: String = project.findProperty("API_KEY") as String? ?: ""

val localProperties = Properties()
localProperties.load(rootProject.file("local.properties").inputStream())


android {
    namespace = "io.github.josephgm.boxofficeapp"
    compileSdk = 34


    defaultConfig {
        applicationId = "io.github.josephgm.boxofficeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val apiKey = localProperties.getProperty("API_KEY") ?: "default_api_key"
        buildConfigField("String", "API_KEY", "\"$apiKey\"")

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        // add for uploading github w/o api_key
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.navigation:navigation-compose:2.7.4")

    //Compose Viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    //Network call
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //JSON to Kotlin object mapping
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Image loading
    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation ("androidx.compose.ui:ui:1.6.7")
    implementation ("androidx.compose.material3:material3:1.2.1")
    implementation ("androidx.compose.animation:animation:1.6.7")
    implementation ("androidx.compose.runtime:runtime:1.2.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}