// Copyright (c) Meta Platforms, Inc. and affiliates.

// This source code is licensed under the MIT license found in the
// LICENSE file in the root directory of this source tree.

plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")

  id("com.google.devtools.ksp")
  id("com.meta.spatial.plugin")
}

android {
  namespace = "com.meta.media.template"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.meta.media.template"
    // Meta Spatial SDK require 28 for minimum SDK version.
    minSdk = 29
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions { jvmTarget = "17" }

  buildFeatures { buildConfig = true }
  flavorDimensions += "device"
  productFlavors {
    create("mobile") { dimension = "device" }
    create("quest") { dimension = "device" }
  }
}

val metaSpatialSdkVersion = "0.8.0"

dependencies {
  implementation("androidx.core:core-ktx:1.9.0")
  implementation("androidx.fragment:fragment-ktx:1.6.2")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.12.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")

  implementation("com.google.code.gson:gson:2.11.0")
  implementation("com.github.bumptech.glide:glide:4.16.0")

  implementation("androidx.media3:media3-exoplayer:1.3.1")
  implementation("androidx.media3:media3-ui:1.3.1")

  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

  implementation("com.meta.spatial:meta-spatial-sdk:$metaSpatialSdkVersion")
  implementation("com.meta.spatial:meta-spatial-sdk-toolkit:$metaSpatialSdkVersion")
  implementation("com.meta.spatial:meta-spatial-sdk-vr:$metaSpatialSdkVersion")
  implementation("com.meta.spatial:meta-spatial-sdk-physics:$metaSpatialSdkVersion")
  ksp("com.meta.spatial.plugin:com.meta.spatial.plugin.gradle.plugin:0.8.0")
}

val projectDir = layout.projectDirectory
val sceneDirectory = projectDir.dir("spatial_editor/MediaApp")
spatial {
  allowUsageDataCollection = true
  scenes {
    exportItems {
      item {
        projectPath.set(sceneDirectory.file("Main.metaspatial"))
        outputPath.set(projectDir.dir("src/quest/assets/scenes"))
      }
    }
  }
}
