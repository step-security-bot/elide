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

@file:Suppress("RedundantVisibilityModifier", "unused", "UNUSED_PARAMETER")

package elide.proto.impl.data

import elide.core.encoding.base64.Base64Data
import elide.core.encoding.hex.HexData
import elide.data.DataContainer
import elide.proto.api.data.DataContainer as IDataContainer

/** TBD. */
public class FlatDataContainer private constructor (private val container: DataContainer) : IDataContainer<
  FlatDataContainer,
  FlatDataContainer.Builder,
  FlatDataFingerprint,
  FlatDataFingerprint.Builder,
  FlatHashAlgorithm,
  FlatEncoding
> {
  /** TBD. */
  public class Builder : IDataContainer.IBuilder<
    FlatDataContainer,
    FlatDataFingerprint,
    FlatDataFingerprint.Builder,
    FlatHashAlgorithm,
    FlatEncoding,
    Builder,
  > {
    override var data: ByteArray
      get() = TODO("Not yet implemented")
      set(value) {}

    override var encoding: FlatEncoding
      get() = TODO("Not yet implemented")
      set(value) {}

    override fun setData(value: ByteArray): Builder {
      TODO("Not yet implemented")
    }

    override fun setData(value: String): Builder {
      TODO("Not yet implemented")
    }

    override fun setBase64(value: Base64Data): Builder {
      TODO("Not yet implemented")
    }

    override fun setHex(value: HexData): Builder {
      TODO("Not yet implemented")
    }

    override fun build(): FlatDataContainer {
      TODO("Not yet implemented")
    }
  }

  /** Factory for protocol buffer-backed data containers. */
  public companion object Factory : IDataContainer.Factory<
    FlatDataContainer,
    Builder,
    FlatDataFingerprint,
    FlatDataFingerprint.Builder,
    FlatHashAlgorithm,
    FlatEncoding,
  > {
    override fun empty(): FlatDataContainer {
      TODO("Not yet implemented")
    }

    override fun copy(model: FlatDataContainer): FlatDataContainer {
      TODO("Not yet implemented")
    }

    override fun defaultInstance(): FlatDataContainer = TODO("not yet implemented")

    override fun create(encoding: FlatEncoding, data: ByteArray): FlatDataContainer {
      TODO("Not yet implemented")
    }

    override fun create(data: ByteArray): FlatDataContainer {
      TODO("Not yet implemented")
    }

    override fun create(base64: Base64Data): FlatDataContainer {
      TODO("Not yet implemented")
    }

    override fun create(hex: HexData): FlatDataContainer {
      TODO("Not yet implemented")
    }

    override fun create(data: String): FlatDataContainer {
      TODO("Not yet implemented")
    }

    override fun builder(): Builder {
      TODO("Not yet implemented")
    }

    override fun create(op: context(Builder) () -> Unit): FlatDataContainer {
      TODO("Not yet implemented")
    }
  }

  override fun factory(): Factory {
    TODO("Not yet implemented")
  }

  override fun bytes(): ByteArray {
    TODO("Not yet implemented")
  }

  override fun encoding(): FlatEncoding? {
    TODO("Not yet implemented")
  }

  override fun fingerprint(): FlatDataFingerprint? {
    TODO("Not yet implemented")
  }

  override fun toBuilder(): Builder {
    TODO("Not yet implemented")
  }

  override fun mutate(op: context(Builder) () -> Unit): FlatDataContainer {
    TODO("Not yet implemented")
  }
}
