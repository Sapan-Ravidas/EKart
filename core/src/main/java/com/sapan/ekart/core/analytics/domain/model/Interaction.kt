package com.sapan.ekart.core.analytics.domain.model

data class Interaction(
    val type: String,
    val timestamp: Long,
    val details: String
)
