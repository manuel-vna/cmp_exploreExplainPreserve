package com.example.cmpexploreexplainpreserve.notifications

expect class NotificationManager {
    fun showNotification(
        title: String,
        description: String
    )
}