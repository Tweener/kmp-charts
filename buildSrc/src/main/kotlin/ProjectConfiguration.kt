import org.gradle.api.JavaVersion

/**
 * @author Vivien Mahe
 * @since 23/07/2022
 */

object ProjectConfiguration {

    object Charts {
        const val packageName = "com.tweener.charts"
        const val versionName = "1.2.1"
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
}
