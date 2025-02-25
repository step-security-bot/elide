package elide.internal.conventions

import org.gradle.api.Project

/** Convention values used across all extensions. */
public object Constants {
  /**
   * The name of the property used to indicate that the project is running in CI.
   * @see isCI
   */
  internal const val CI_FLAG = "elide.ci"

  /** Property names and env variables related to credentials. */
  internal object Credentials {
    /** Environment variable: path to the Google Cloud credentials file. */
    const val GOOGLE = "GOOGLE_APPLICATION_CREDENTIALS"

    /** Environment variable: username to be used when publishing packages. */
    const val PUBLISH_USER = "PUBLISH_USER"

    /** Environment variable: token to be used when publishing packages. */
    const val PUBLISH_TOKEN = "PUBLISH_TOKEN"

    /** Environment variable: username to be used when authenticating to GitHub. */
    const val GITHUB_ACTOR = "GITHUB_ACTOR"

    /** Environment variable: token to be used when authenticating to GitHub. */
    const val GITHUB_TOKEN = "GITHUB_TOKEN"

    /** Property: username to be used when publishing Maven packages. */
    const val MAVEN_USER = "elide.publish.repo.maven.username"

    /** Property: password to be used when publishing Maven packages. */
    const val MAVEN_PASSWORD = "elide.publish.repo.maven.password"
  }

  /** General publishing conventions. */
  internal object Publishing {
    /** Property: whether authentication should be used for Maven repositories. */
    const val MAVEN_AUTH_REQUIRED = "elide.publish.repo.maven.auth"

    /** Property: URL of the Maven repo to be used for publishing. */
    const val MAVEN_REPO_URL = "elide.publish.repo.maven"
  }

  /** Remote repositories used for publishing, etc. */
  internal object Repositories {
    /** Project-specific Maven repository on GitHub. */
    const val GITHUB_MAVEN = "https://maven.pkg.github.com/elide-dev/elide"

    /** Docker container registry. */
    const val PKG_DOCKER = "https://us-docker.pkg.dev"
  }

  /** Values for internal test conventions. */
  internal object Tests {
    /** Sets the threshold at which tests are considered as 'slow'. */
    const val SLOW_TEST_THRESHOLD = 30_000L

    /** Maximum number of forks allowed during parallel test execution. */
    const val MAX_PARALLEL_FORKS = 4
  }

  /** Properties and conventions related to versioning. */
  internal object Versions {
    /** Property: defines the Kotlin language version used in the project. */
    const val KOTLIN = "versions.kotlin.language"

    /** Property: defines the version of the Kotlin SDK. */
    const val KOTLIN_SDK = "versions.kotlin.sdk"

    /** Constant: default Kotlin version. */
    const val KOTLIN_DEFAULT = "1.9"

    /** Property: defines the target JVM version. */
    const val JVM_TARGET = "versions.java.target"

    const val GRAALVM_METADATA = "0.3.3"
  }

  /** Kotlin conventions. */
  internal object Kotlin {
    /** Property: whether to treat all warnings as errors. */
    const val STRICT_MODE = "strictMode"

    /** Enumerates the target platforms for JavaScript projects. */
    enum class JavaScriptTarget {
      BROWSER,
      NODE_JS,
    }
  }

  internal object Build {
    /** Property: whether to build and bundle documentation from project sources, defaults to "true". */
    const val BUILD_DOCS = "buildDocs"
  }

  /** Static library configuration values. */
  public object Elide {
    /** Major release version for Elide. */
    private const val TRACK = "v3"

    /** Major library version. */
    private const val MAJOR_VERSION = "1.0"

    /** Major library version tag. */
    private const val VERSION_TAG = "alpha6"

    /** Revision value for the library. */
    private const val REVISION = 15

    /** Version string for the library. */
    public const val VERSION: String = "$MAJOR_VERSION-$TRACK-$VERSION_TAG-b$REVISION"

    /** Maven group shared by Elide artifacts. */
    public const val GROUP: String = "dev.elide"

    /** Maven group shared by tooling projects. */
    public const val SUBSTRATE_GROUP: String = "dev.elide.tools"

    /** Compiler args to include in all Kotlin targets. */
    private val BaseCompilerArgs = listOf(
      "-progressive",
      "-Xcontext-receivers",
      "-Xskip-prerelease-check",
      "-Xexpect-actual-classes",
    )

    /** Compiler args to include in Kotlin JVM targets. */
    internal val JvmCompilerArgs = BaseCompilerArgs.plus(
      listOf(
        "-no-stdlib",
        "-Xjvm-default=all",
        "-Xjsr305=strict",
      ),
    )

    /** Compiler args to include in Kotlin JS targets. */
    internal val JsCompilerArgs = BaseCompilerArgs.plus(
      listOf(
        "-Xgenerate-dts",
      ),
    )

    /** Compiler args to include in Kotlin JVM targets which use `kapt`. */
    internal val KaptCompilerArgs = BaseCompilerArgs.plus(
      listOf(
        "-no-stdlib",
        "-Xallow-unstable-dependencies",
        "-Xemit-jvm-type-annotations",
        "-Xjvm-default=all",
        "-Xjsr305=strict",
      ),
    )

    /** Compiler args to include in KMP targets. */
    internal val KmpCompilerArgs = BaseCompilerArgs
  }
}

/** Whether this [Project] is running in CI, as indicated by the [CI_FLAG][Constants.CI_FLAG] property. */
internal val Project.isCI get() = findProperty(Constants.CI_FLAG)?.toString().toBoolean()
