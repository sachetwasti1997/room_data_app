package com.sachet.appdataroom.db

import androidx.room.Entity

@Entity
data class Subscriber(
    val id: Int,
    val name: String,
    val email: String
)
