package com.example.learning.domain.models

import com.example.learning.domain.util.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String,
)