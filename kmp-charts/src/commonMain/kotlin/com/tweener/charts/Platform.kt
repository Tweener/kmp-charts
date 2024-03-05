package com.tweener.charts

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
