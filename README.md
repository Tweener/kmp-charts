[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.tweener/kmp-charts?color=orange)](https://central.sonatype.com/artifact/io.github.tweener/kmp-charts)
[![Kotlin](https://img.shields.io/badge/kotlin-2.0.21-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Compose](https://img.shields.io/badge/compose-1.7.1-blue.svg?logo=jetpackcompose)](https://www.jetbrains.com/lp/compose-multiplatform)
![gradle-version](https://img.shields.io/badge/gradle-8.5.2-blue?logo=gradle)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://opensource.org/licenses/Apache-2.0)

[![Website](https://img.shields.io/badge/Author-vivienmahe.com-purple)](https://vivienmahe.com/)
[![X/Twitter](https://img.shields.io/twitter/follow/VivienMahe)](https://twitter.com/VivienMahe)

---

# kmp-charts

**kmp-charts** is a Kotlin/Compose Multiplatform library that offers various types of charts to display data.

<br>

Be sure to show your support by starring ⭐️ this repository, and feel free to [contribute](#-contributing) if you're interested!

---

## 🛠️ Installation

Add the dependency in your common module's commonMain `sourceSet`:

In your `settings.gradle.kts` file, add Maven Central to your repositories:
```Groovy
repositories {
    mavenCentral()
}
```

Then add kmp-charts dependency to your module:

- With version catalog, open `libs.versions.toml`:
```Groovy
[versions]
kmpCharts = "1.3.1" // Check latest version

[libraries]
kmpCharts = { group = "io.github.tweener", name = "kmp-charts", version.ref = "kmpCharts" }
```

Then in your module `build.gradle.kts` add:
```Groovy
dependencies {
    implementation(libs.kmpCharts)
}
```

- Without version catalog, in your module `build.gradle.kts` add:
```Groovy
dependencies {
    val kmp_charts_version = "1.3.1" // Check latest version

    implementation("io.github.tweener:kmp-charts:$kmp_charts_version")
}
```

The latest version is: [![Maven Central Version](https://img.shields.io/maven-central/v/io.github.tweener/kmp-charts?color=orange)](https://central.sonatype.com/artifact/io.github.tweener/kmp-charts)

## 🧑‍💻 Usage

### 🍩 Donut chart
A [`DonutChart`](https://github.com/Tweener/kmp-charts/blob/main/kmp-charts/src/commonMain/kotlin/com/tweener/charts/DonutChart.kt#L61) requires a list of [Segment](https://github.com/Tweener/kmp-charts/blob/main/kmp-charts/src/commonMain/kotlin/com/tweener/charts/DonutChart.kt#L43)s, with the first segment starting from the given `startAngleFromOrigin` in degrees.
Each segment is defined by an **angle**, its **color** and an optional **progress** option.

_See [`.degrees`](https://github.com/Tweener/KMPKit/blob/main/kmpkit/src/commonMain/kotlin/com/tweener/kmpkit/kotlinextensions/FloatExtensions.kt#L15) to easily use float angles in degrees._ 

```Kotlin
val green = Color(0xFF04C700)
val orange = Color(0xFFFF8850)
val red = Color(0xFFFF3434)
val darkRed = Color(0xFFA40000)
val yellow = Color(0xFFFFF534)
val darkYellow = Color(0xFF746F0E)
val blue = Color(0xFF3437FF)

DonutChart(
    segments = listOf(
        Segment(angle = 40f.degrees, progress = 0.33f, baseColor = green),
        Segment(angle = 20f.degrees, progress = 0.7f, baseColor = yellow, backgroundColor = darkYellow),
        Segment(angle = 90f.degrees, progress = 0.66f, baseColor = green),
        Segment(angle = 60f.degrees, progress = 0.7f, baseColor = red, backgroundColor = darkRed),
        Segment(angle = 50f.degrees, progress = 0.8f, baseColor = orange),
        Segment(angle = 100f.degrees, progress = 1f, baseColor = blue),
    ),
    startAngleFromOrigin = 270f.degrees,
    sizes = DonutChartDefault.chartSizes(strokeWidth = 12.dp, selectedStrokeWidth = 22.dp),
    animationDurationMillis = 800,
)
```
This code gives the following output:

<img src="https://github.com/Tweener/kmp-charts/assets/596985/9b1a82dd-6358-4d6e-af2c-cbb3bfe67258" width="350">

### 📈 Line chart
A LineChart is a versatile chart used to visualize data points connected by straight or curved lines. It is ideal for displaying trends, relationships, or changes over time.

```Kotlin
LineChart(
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
    lines = lines,
    xAxis = xAxis,
    yAxis = yAxis,
    textStyle = MaterialTheme.typography.labelLarge,
    gridVisibility = LineChartDefaults.gridVisibility(
        showXAxis = true,
        showYAxis = true,
    ),
    colors = LineChartDefaults.chartColors(
        xAxisValues = MaterialTheme.colorScheme.onBackground,
        xAxisGrid = MaterialTheme.colorScheme.outline,
        yAxisValues = MaterialTheme.colorScheme.onBackground,
        yAxisGrid = MaterialTheme.colorScheme.outline,
    ),
    sizes = LineChartDefaults.chartSizes(
        axisStrokeWidth = 1.dp,
        axisDashOn = 8.dp,
        axisDashOff = 8.dp,
        axisXValuesPadding = Size.Padding.Small,
        axisYValuesPadding = Size.Padding.ExtraSmall,
    )
)
```
Some examples of output with straight or curved lines:

![Screenshot 2024-12-18 at 09 17 14](https://github.com/user-attachments/assets/3370e044-450a-4f5c-966e-001213904535)
![Screenshot 2024-12-18 at 09 17 55](https://github.com/user-attachments/assets/66cd4a05-42a1-4805-8836-aedc446ef74e)

---

## 👨‍💻 Contributing

We love your input and welcome any contributions! Please read our [contribution guidelines](https://github.com/Tweener/kmp-charts/blob/master/CONTRIBUTING.md) before submitting a pull request.

---

## 📜 Licence

kmp-charts is licensed under the [Apache-2.0](https://github.com/Tweener/kmp-charts?tab=Apache-2.0-1-ov-file#readme).
