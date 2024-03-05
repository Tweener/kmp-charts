import org.gradle.api.JavaVersion

/**
 * @author Vivien Mahe
 * @since 23/07/2022
 */

object Dependencies {

    object Versions {

        const val kotlin = "1.9.21"
        const val gradle = "8.1.4"
        const val dependencyVersionsPlugin = "0.51.0"
        const val nexusSonatype = "2.0.0-rc-1"
        const val dokka = "1.9.10"
        const val composeMultiplatform = "1.6.0"
        const val composeCompilerExtension = "1.5.9"
        const val annotations = "1.7.1"
        const val coroutines = "1.7.3"
        const val napier = "2.6.1"

        object Charts {
            const val packageName = "com.tweener.charts"
            const val versionName = "1.0.0"
            const val namespace = "$packageName.android"
            const val compileSDK = 34
            const val minSDK = 24

            object Maven {
                const val name = "KMPCharts"
                const val description = "A Kotlin/Compose Multiplatform library that offers many different types of charts"
                const val group = "io.github.tweener"
                const val packageUrl = "https://github.com/Tweener/kmp-charts"
                const val gitUrl = "github.com:Tweener/kmp-charts.git"

                object Developer {
                    const val id = "Tweener"
                    const val name = "Vivien Mahé"
                    const val email = "vivien@tweener-labs.com"
                }
            }
        }

        object Compiler {
            const val jvmTarget = "17"
            val javaCompatibility = JavaVersion.VERSION_17
        }

        object Tweener {
            const val czan = "2.1.0"
            const val bom = "1.0.0"
        }

        object Android {
            const val desugarJdkLibs = "2.0.3"

            object AndroidX {
                const val core = "1.12.0"
            }
        }
    }

    object Libraries {

        const val napier = "io.github.aakira:napier:${Versions.napier}"
        const val annotations = "androidx.annotation:annotation:${Versions.annotations}"

        object Tweener {
            const val czan = "io.github.tweener:czan:${Versions.Tweener.czan}"

            const val bom = "io.github.tweener:kmp-bom:${Versions.Tweener.bom}"
            const val common = "io.github.tweener:kmp-common"
        }

        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

            object Android {
                const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
            }
        }

        object ComposeMultiplatform {
            const val material3 = "org.jetbrains.compose.material3:material3:${Versions.composeMultiplatform}"
        }

        object Android {
            const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions.Android.desugarJdkLibs}"

            object AndroidX {
                const val core = "androidx.core:core-ktx:${Versions.Android.AndroidX.core}"
            }
        }
    }
}
