// Copyright (c) Meta Platforms, Inc. and affiliates.

// This source code is licensed under the MIT license found in the
// LICENSE file in the root directory of this source tree.

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  id("com.android.application") version "8.1.0" apply false
  id("org.jetbrains.kotlin.android") version "2.0.20" apply false

  id("com.meta.spatial.plugin") version "0.8.0" apply true
  id("com.google.devtools.ksp") version "2.0.20-1.0.24" apply true
}
