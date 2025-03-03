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

package elide.runtime.gvm.internals.intrinsics.js.struct.map

import java.util.*
import elide.runtime.gvm.internals.intrinsics.js.JsError.jsErrors
import elide.runtime.intrinsics.js.MutableMapLike
import elide.runtime.intrinsics.js.err.TypeError
import elide.vm.annotations.Polyglot

/** Abstract implementation of a mutable JS map, backed by a Java map. */
internal sealed class BaseMutableJsMap<K: Any, V> constructor (
  map: MutableMap<K, V>,
  threadsafe: Boolean = false,
  multi: Boolean = false,
  sorted: Boolean = false,
) : BaseJsMap<K, V> (
  map,
  threadsafe = threadsafe,
  multi = multi,
  sorted = sorted,
  mutable = true,
), MutableMapLike<K, V> {
  // Cast as a mutable map.
  private fun asMutable(): MutableMap<K, V> = backingMap as MutableMap<K, V>

  /** @inheritDoc */
  override val keys: MutableSet<K> get() = asMutable().keys

  /** @inheritDoc */
  override val values: MutableCollection<V> get() = asMutable().values

  /** @inheritDoc */
  override val entries: MutableSet<MutableMap.MutableEntry<K, V>> get() = asMutable().entries

  /** @inheritDoc */
  override fun put(key: K, value: V): V? {
    val previousValue = backingMap[key]
    asMutable()[key] = value
    return previousValue
  }

  /** @inheritDoc */
  override fun putAll(from: Map<out K, V>) = asMutable().putAll(from)

  /** @inheritDoc */
  override fun putIfAbsent(key: K, value: V): V? = asMutable().putIfAbsent(key, value)

  /** @inheritDoc */
  @Polyglot override fun set(key: K, value: V) {
    asMutable()[key] = value
  }

  /** @inheritDoc */
  override fun remove(key: K): V? {
    val valueToDelete = backingMap[key]
    if (valueToDelete != null) {
      asMutable().remove(key)
    }
    return valueToDelete
  }

  /** @inheritDoc */
  @Polyglot override fun clear() = asMutable().clear()

  /** @inheritDoc */
  @Polyglot override fun delete(key: K) {
    asMutable().remove(key)
  }

  /** @inheritDoc */
  @Throws(TypeError::class)
  @Polyglot override fun sort() = jsErrors {
    if (backingMap.isNotEmpty()) {
      backingMap = TreeMap<K, V>(backingMap)
    }
  }

  /** @inheritDoc */
  abstract override fun toString(): String
}
