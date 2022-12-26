package com.example.task_10.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task_10.db.dao.SettingDao
import com.example.task_10.db.dao.UserDao
import com.example.task_10.db.entity.SettingEntity
import com.example.task_10.db.entity.UserEntity

@Database(entities = [UserEntity::class, SettingEntity::class], version = DatabaseHandler.versiondb)
abstract class InceptionDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getSettingDao(): SettingDao
}