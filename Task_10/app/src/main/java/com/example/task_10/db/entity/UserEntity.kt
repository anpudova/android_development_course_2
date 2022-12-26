package com.example.task_10.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users", indices = [Index("username", unique = true)])
data class UserEntity(
    @PrimaryKey val id: Int,
    val username: String,
    val password: String
)
