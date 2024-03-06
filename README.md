# kmp-charts

kmp-charts is a Kotlin/Compose Multiplatform library that offers many different types of charts to display data.

## 💾 Installation

Add the dependency in your common module's commonMain `sourceSet`:

```groovy
implementation('io.github.tweener:kmp-charts:$kmp_charts_version')
```

_The latest version is: [![](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fs01.oss.sonatype.org%2Fservice%2Flocal%2Frepo_groups%2Fpublic%2Fcontent%2Fio%2Fgithub%2Ftweener%2Fkmp-charts%2Fmaven-metadata.xml)](https://central.sonatype.com/artifact/io.github.tweener/kmp-charts)_

## ⚙️ Usage

> [!NOTE]
> For now, there is only one chart available: [`DonutChart`](https://github.com/Tweener/kmp-charts/blob/main/kmp-charts/src/commonMain/kotlin/com/tweener/charts/DonutChart.kt#L61). More charts will be added later on.

#### 🍩 Donut chart
A [`DonutChart`](https://github.com/Tweener/kmp-charts/blob/main/kmp-charts/src/commonMain/kotlin/com/tweener/charts/DonutChart.kt#L61) requires a list of [Segment](https://github.com/Tweener/kmp-charts/blob/main/kmp-charts/src/commonMain/kotlin/com/tweener/charts/DonutChart.kt#L43)s, with the first segment starting from the given `startAngleFromOrigin` in degrees.
Each segment is defined by an **angle**, its **color** and an optional **progress** option.

_See [`.degrees`](https://github.com/Tweener/kmp-bom/blob/main/kmp-common/src/commonMain/kotlin/com/tweener/common/_internal/kotlinextensions/FloatExtension.kt#L14) to easily use float angles in degrees._ 

```kotlin
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

## 👨‍💻 Contributing

I'd love your input and welcome any contributions! Please feel free to submit a pull request.

## 🪪 Licence

kmp-charts is licensed under the [Apache-2.0](https://github.com/Tweener/kmp-charts?tab=Apache-2.0-1-ov-file#readme).
