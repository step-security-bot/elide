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

package elide.runtime.intrinsics

import org.graalvm.polyglot.Value
import org.graalvm.polyglot.proxy.ProxyObject
import java.util.*
import java.util.function.BiFunction
import java.util.function.Function
import elide.runtime.gvm.GuestLanguage
import elide.runtime.gvm.internals.intrinsics.js.JsSymbol

/**
 * # Guest Intrinsic
 *
 * Applied to all intrinsic classes which are implemented for a guest language, in addition to various annotations which
 * designate the use context of a given implementation.
 */
public interface GuestIntrinsic {
  /**
   * TBD.
   */
  public interface IntrinsicBindings : Map<Symbol, Any>

  /**
   * TBD.
   */
  public interface MutableIntrinsicBindings : IntrinsicBindings, MutableMap<Symbol, Any>, ProxyObject {
    /** factory for creating empty bindings. */
    public object Factory {
      /** @return Mutable intrinsic bindings backed by a map. */
      @JvmStatic public fun create(): MutableIntrinsicBindings = wrap(TreeMap<Symbol, Any>())

      /** @return Mutable intrinsic bindings backed by a map. */
      @JvmStatic public fun wrap(target: MutableMap<Symbol, Any>): MutableIntrinsicBindings {
        val bindingSet = TreeSet<String>()
        return object: MutableIntrinsicBindings, MutableMap<Symbol, Any> by target {
          // Check the uniqueness of an intrinsic binding name.
          private fun checkName(key: Symbol) {
            check(key.symbol !in bindingSet) {
              "Intrinsic binding '$key' is already bound."
            }
          }

          // Throw a consistent error for removals, which are not allowed.
          private fun notAllowed(): Nothing = error(
            "Operation not allowed on intrinsic binding proxy."
          )

          /** Removing intrinsics is not allowed; this method always throws. */
          override fun remove(key: Symbol): Any = notAllowed()

          /** Clearing intrinsics is not allowed; this method always throws. */
          override fun clear() = notAllowed()

          override fun put(key: Symbol, value: Any): Any? {
            checkName(key)
            bindingSet.add(key.symbol)
            return target.put(key ,value)
          }

          override fun putAll(from: Map<out Symbol, Any>) {
            from.keys.forEach {
              checkName(it)
              bindingSet.add(it.symbol)
              target[it] = from[it]!!
            }
          }

          override fun compute(key: Symbol, remappingFunction: BiFunction<in Symbol, in Any?, out Any?>)
            = notAllowed()

          override fun computeIfAbsent(key: Symbol, mappingFunction: Function<in Symbol, out Any>)
            = notAllowed()

          override fun computeIfPresent(key: Symbol, remappingFunction: BiFunction<in Symbol, in Any, out Any?>)
            = notAllowed()

          override fun getMember(key: String): Any = target[JsSymbol(key)] ?:
            throw IllegalArgumentException("Intrinsic '$key' could not be resolved: not bound.")

          override fun getMemberKeys(): Any = bindingSet.toTypedArray()

          override fun hasMember(key: String): Boolean = bindingSet.contains(key)

          override fun putMember(key: String, value: Value?) = error(
            "Cannot assign to `Intrinsics` members at runtime"
          )
        }
      }
    }
  }

  /**
   * Indicate the language which this intrinsic is intended to be used with.
   *
   * @return Guest language bound to this intrinsic.
   */
  public fun language(): GuestLanguage

  /**
   * Indicate whether this intrinsic is intended to be used with a given guest [language].
   *
   * @param language Language to check.
   * @return `true` if this intrinsic is intended to be used with the given language, `false` otherwise.
   */
  public fun supports(language: GuestLanguage): Boolean {
    return language().symbol == language.symbol
  }

  /**
   * Install this intrinsic into the provided context [bindings] for a fresh context; this will only be called once per
   * spawned context.
   *
   * The default (abstract) implementation of this method should scan for intrinsics on the current object and mount
   * each at their specified global, as applicable.
   *
   * @param bindings Language bindings target where the intrinsic should be installed.
   */
  public fun install(bindings: MutableIntrinsicBindings)

  /**
   * TBD.
   */
  public fun displayName(): String
}
