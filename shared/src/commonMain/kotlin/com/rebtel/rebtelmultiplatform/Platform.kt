package com.rebtel.rebtelmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform