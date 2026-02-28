package com.example.cmpexploreexplainpreserve.koin

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
private var koinInitialized = false

fun initKoinIos() {
    if (!koinInitialized) {
        initKoin()
        koinInitialized = true
    }
}
