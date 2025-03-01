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

package elide.runtime.gvm

import io.micronaut.context.BeanContext
import io.micronaut.context.annotation.Prototype
import io.micronaut.http.HttpRequest
import java.util.ServiceLoader
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlin.reflect.KClass
import elide.annotations.Factory
import elide.annotations.Inject
import elide.annotations.Singleton
import elide.runtime.gvm.api.GuestRuntime
import elide.runtime.gvm.cfg.GuestRuntimeConfiguration
import elide.runtime.gvm.internals.AbstractVMEngine
import elide.runtime.gvm.internals.context.ContextManager
import elide.runtime.gvm.internals.js.JsRuntime
import org.graalvm.polyglot.Context as VMContext


public typealias GenericEngine = (
  AbstractVMEngine<out GuestRuntimeConfiguration, out ExecutableScript, out InvocationBindings>
)

/**
 * TBD.
 */
@Singleton public class VMFacadeFactory @Inject internal constructor (
  // VM execution bridge.
  private val contextManager: ContextManager<VMContext, VMContext.Builder>,

  // Active bean context.
  private val beanContext: BeanContext,

  // All DI-available engines.
  private val engines: Collection<GenericEngine>,
) {
  /**
   * TBD.
   */
  internal class CompoundVMFacade private constructor (private val engines: Array<out VMFacade>)
    : VMEngineImpl<GuestRuntimeConfiguration> {
    internal companion object {
      /**
       * TBD.
       */
      @JvmStatic fun withEngines(engines: List<VMFacade>) = CompoundVMFacade(engines.toTypedArray())
    }

    override fun language(): GuestLanguage {
      TODO("Not yet implemented")
    }

    override suspend fun prewarmScript(script: ExecutableScript) {
      TODO("Not yet implemented")
    }

    override suspend fun executeStreaming(
      script: ExecutableScript,
      args: ExecutionInputs,
      receiver: StreamingReceiver
    ): Job {
      TODO("Not yet implemented")
    }

    override suspend fun executeRender(
      script: ExecutableScript,
      request: HttpRequest<*>,
      context: Any?,
      receiver: StreamingReceiver
    ): Job {
      if (engines.size > 1) error("Cannot execute with multiple VM engines")
      return engines.first().executeRender(script, request, context, receiver)
    }

    override suspend fun <R> execute(script: ExecutableScript, returnType: Class<R>, args: ExecutionInputs?): R? {
      TODO("Not yet implemented")
    }

    override suspend fun <R> executeAsync(
      script: ExecutableScript,
      returnType: Class<R>,
      args: ExecutionInputs?
    ): Deferred<R?> {
      TODO("Not yet implemented")
    }

    override fun <R> executeBlocking(script: ExecutableScript, returnType: Class<R>, args: ExecutionInputs?): R? {
      TODO("Not yet implemented")
    }
  }

  // Lazy resolution of VM engine implementations.
  private val installedEngines by lazy {
    ServiceLoader.load(VMEngineImpl::class.java)
  }

  // Resolve the VM implementation class to use for the provided `language` via static means (built-in VMs only).
  private fun resolveStaticVMFactoryImpl(language: GuestLanguage): KClass<*>? = when (language.symbol) {
    "js" -> JsRuntime::class
    else -> null
  }

  // Resolve the VM implementation class to use for the provided `language` from the active bean context.
  private fun resolveBuildTimeInjectedVMactoryImpl(language: GuestLanguage): GenericEngine? = engines.find { engine ->
    engine.language().symbol == language.symbol
  }

  // Load a VM implementation via a service-loader.
  private fun resolveDynamicVMFactoryImpl(language: GuestLanguage): KClass<*>? = installedEngines.let { engines ->
    engines.stream().filter {
      it.type().annotations.find { anno ->
        anno.annotationClass.qualifiedName == GuestRuntime::class.qualifiedName
      }?.let { anno ->
        val runtime = anno as GuestRuntime
        runtime.engine == language.symbol
      } ?: false
    }.findFirst().let { candidate ->
      if (candidate.isEmpty) {
        null
      } else {
        candidate.get().type().kotlin
      }
    }
  }

  // Initialize a VM engine after loading it from the service loader; this is done by acquiring an instance through the
  // active bean context.
  private fun initializeVMEngine(impl: KClass<*>, instance: AbstractVMEngine<*, *, *>?): AbstractVMEngine<*, *, *> {
    return (instance ?: (beanContext.getBean(impl.java) as AbstractVMEngine<*, *, *>)).apply {
      this@apply.contextManager = this@VMFacadeFactory.contextManager
      initialize()
    }
  }

  /**
   * TBD.
   */
  public fun acquireVM(vararg languages: GuestLanguage): VMFacade {
    val engines = languages.map {
      when (val instance = resolveBuildTimeInjectedVMactoryImpl(it)) {
        null -> {
          val impl = (
            resolveStaticVMFactoryImpl(it) ?:
            resolveDynamicVMFactoryImpl(it)
          ) ?: error(
            "Failed to resolve VM implementation for language: ${it.label}. Is it supported and installed?"
          )
          object: VMFacade by initializeVMEngine(impl, null) {
            /* No overrides at this time. */
          }
        }

        // use the existing instance, if found
        else -> object: VMFacade by initializeVMEngine(instance::class, instance) {
          /* No overrides at this time. */
        }
      }
    }
    return CompoundVMFacade.withEngines(
      engines
    )
  }

  /**
   * TBD.
   */
  @Singleton @Factory internal fun acquireContextManager(): ContextManager<VMContext, VMContext.Builder> {
    // acquire a VM to initialize context
    acquireVM(GuestLanguage.JAVASCRIPT)
    return contextManager
  }
}
