package com.example.cmpexploreexplainpreserve

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform