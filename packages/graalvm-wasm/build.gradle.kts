/*
 * Copyright (c) 2023 Elide Ventures, LLC.
 *
 * Licensed under the MIT license (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   https://opensource.org/license/mit/
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License.
 */

import elide.internal.conventions.elide
import elide.internal.conventions.publishing.publish
import elide.internal.conventions.native.NativeTarget
import elide.internal.conventions.kotlin.KotlinTarget

plugins {
  alias(libs.plugins.micronaut.library)
  alias(libs.plugins.micronaut.graalvm)

  kotlin("jvm")
  kotlin("kapt")
  kotlin("plugin.allopen")

  id("elide.internal.conventions")
}

elide {
  publishing {
     id = "graalvm-wasm"
    name = "Elide WASM for GraalWasm"
    description = "Integration package with GraalVM WASM and Elide."

    publish("jvm") {
      from(components["kotlin"])
    }
  }
  
  kotlin {
    target = KotlinTarget.JVM
    explicitApi = true
  }

  java {
    // disable module-info processing (not present)
    configureModularity = false
  }

  native {
    target = NativeTarget.LIB
  }
}

dependencies {
  api(libs.graalvm.polyglot.wasm)
  implementation(libs.kotlinx.coroutines.core)
  implementation(projects.packages.graalvm)

  // Testing
  testImplementation(projects.packages.test)
}
