package com.thanhvo.todothanhvo.tasklist

data class Task (
    val id: String,
    val title: String,
    val description: String = "Message par default"
)