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

package elide.runtime.feature.engine

import org.graalvm.nativeimage.hosted.Feature.BeforeAnalysisAccess
import org.graalvm.nativeimage.hosted.Feature.IsInConfigurationAccess
import elide.annotations.internal.VMFeature

/** Registers native transport libraries for static JNI. */
@VMFeature internal class NativeTransportFeature : AbstractStaticNativeLibraryFeature() {
  override fun getDescription(): String = "Registers native transport libraries"

  override fun isInConfiguration(access: IsInConfigurationAccess): Boolean {
    return (
      access.findClassByName("io.netty.channel.kqueue.KQueue") != null ||
      access.findClassByName("io.netty.channel.epoll.Epoll") != null ||
      access.findClassByName("io.netty.incubator.channel.uring.IOUring") != null
    )
  }

  override fun nativeLibs(access: BeforeAnalysisAccess) = listOf(
    // Native Transport: Linux
    nativeLibrary(linux = libraryNamed("netty_transport_native_epoll")),
    nativeLibrary(linux = libraryNamed("netty_quiche_linux")),

    // Native Transport: Darwin
    nativeLibrary(darwin = libraryNamed("netty_transport_native_kqueue")),
    nativeLibrary(darwin = libraryNamed("netty_resolver_dns_native_macos")),
    nativeLibrary(darwin = libraryNamed("netty_quiche_osx")),
  )
}
