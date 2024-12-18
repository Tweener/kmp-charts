# Changelog

## [1.3.1] - December 18, 2024
- 🚀 Upgrade to Compose Multiplatform 1.7.1
- Fixes line drawing when only two points in `LineChart`: When a curved line only has two points, use `path.quadraticTo()`, otherwise use `path.cubicTo()`.
- Use [KMPKit](https://github.com/Tweener/KMPKit) instead of [kmp-common](https://github.com/Tweener/kmp-bom/tree/main/kmp-common).

## [1.3.0] - October 17, 2024
- 🚀 Upgrade to Kotlin 2.0.21 & Gradle 8.5.2
- 🚀 Upgrade to Compose Multiplatform 1.7.0
- Grid X & Y axes now have their own individual padding
- `LineChart`: Ensure first and last values on the X axis are within the axis bounds

## [1.2.0] - September 27, 2024
- **`[BREAKING]`** `DonutChart` has been moved to a new package: com.tweener.charts.type.donut.
- **`[BREAKING]`** `Segment` class has been moved to a new package: com.tweener.charts.type.donut.model.
- New chart added: `LineChart` to display plotted points connected with a straight or curved line.
- Fixes a bug where `DonutChart`'s segments were not properly updated

## [1.1.0] - May 28, 2024
- **kmp-charts** now official support Kotlin 2.0! 🎉

## [1.0.1] - April 28, 2024
- `DonutChart` - Each segment now has an ID to better identify which segment has been clicked by the user.
- `DonutChart` - Each segment can now be enabled/disabled to whether or not respond to click events.

## [1.0.0] - March 6, 2024

### 🚀 Initial Release

The first official release of **kmp-charts**.

#### Features
  - DonutChart
