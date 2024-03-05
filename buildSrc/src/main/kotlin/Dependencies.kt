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
        const val annotations = "1.7.1"
        const val coroutines = "1.7.3"
        const val napier = "2.6.1"

        // TODO Rename MyProject to your project name
        object MyProject {
            const val packageName = "com.tweener.changehere" // TODO Change this
            const val versionName = "1.0.0"
            const val namespace = "$packageName.android"
            const val compileSDK = 34
            const val minSDK = 24

            // TODO Change all the values in this block to your needs
            object Maven {
                const val name = "MyKMPLibrary"
                const val description = "All Tweener commons stuff for Kotlin Multiplatform"
                const val group = "io.github.tweener"
                const val packageUrl = "https://github.com/Tweener/kmp-common"
                const val gitUrl = "github.com:Tweener/kmp-common.git"

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
            const val bom = "io.github.tweener:kmp-bom:${Versions.Tweener.bom}"
            const val common = "io.github.tweener:kmp-common"
        }

        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

            object Android {
                const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
            }
        }

        object Android {
            const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions.Android.desugarJdkLibs}"

            object AndroidX {
                const val core = "androidx.core:core-ktx:${Versions.Android.AndroidX.core}"
            }
        }
    }
}
