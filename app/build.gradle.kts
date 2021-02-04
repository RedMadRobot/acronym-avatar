plugins {
    id(Android.applicationPlugin)

    kotlin(Kotlin.androidPlugin)
    kotlin(Kotlin.androidExtensions)
}

android {
    compileSdkVersion(Android.compileSdk)
    buildToolsVersion(Android.buildTools)

    defaultConfig {
        applicationId = Android.DefaultConfig.applicationId

        minSdkVersion(Android.DefaultConfig.minSdk)
        targetSdkVersion(Android.DefaultConfig.targetSdk)

        versionCode = Android.DefaultConfig.versionCode
        versionName = Android.DefaultConfig.versionName

        testInstrumentationRunner = Android.DefaultConfig.instrumentationRunner


        buildTypes {
            getByName(Android.BuildTypes.release) {
                isMinifyEnabled = false

                proguardFiles(
                    getDefaultProguardFile(Android.Proguard.androidOptimizedRules),
                    Android.Proguard.projectRules
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
    }
}

dependencies {
    implementation(project(":acronymavatar"))

    implementation(Kotlin.stdLib)
    implementation(Dependencies.Common.appCompat)
    implementation(Dependencies.App.constraintlayout)
}
