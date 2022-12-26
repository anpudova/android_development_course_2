package com.example.task_10.db

import android.content.Context
import androidx.room.Room
import com.example.task_10.db.entity.SettingEntity
import com.example.task_10.db.entity.UserEntity
import com.example.task_10.db.mapper.SettingMapper
import com.example.task_10.db.mapper.UserMapper
import com.example.task_10.db.model.SettingModel
import com.example.task_10.db.model.UserModel
import com.example.task_10.db.model.UserUpdateModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseHandler {

    const val versiondb: Int = 1
    const val namedb: String = "database"
    private var roomDatabase: InceptionDatabase? = null

    fun dbInit(appContext: Context) {
        if (roomDatabase == null) {
            roomDatabase = Room.databaseBuilder(
                appContext,
                InceptionDatabase::class.java,
                namedb
            ).build()
        }
    }

    suspend fun createUser(user: UserModel) {
        withContext(Dispatchers.IO) {
            roomDatabase?.getUserDao()?.createUser(UserMapper.mapUserEntity(user))
        }
    }

    suspend fun updateUser(user: UserUpdateModel) {
        withContext(Dispatchers.IO) {
            roomDatabase?.getUserDao()?.updateUserData(user)
        }
    }

    suspend fun getUser(username: String, password: String): UserModel? {
        return withContext(Dispatchers.IO) {
            val user: UserEntity? = roomDatabase?.getUserDao()?.getUser(username, password)
            UserMapper.mapUserModel(user)
        }
    }

    suspend fun getUsername(username: String): String? {
        return withContext(Dispatchers.IO) {
            roomDatabase?.getUserDao()?.getUsername(username)
        }
    }

    suspend fun createSettings(settings: SettingModel) {
        withContext(Dispatchers.IO) {
            roomDatabase?.getSettingDao()?.createSettings(SettingMapper.mapSettingEntity(settings))
        }
    }

    suspend fun updateSettings(settings: SettingEntity) {
        withContext(Dispatchers.IO) {
            roomDatabase?.getSettingDao()?.updateSettingData(settings)
        }
    }

    suspend fun getSettings(id: Int): SettingModel? {
        return withContext(Dispatchers.IO) {
            val settings: SettingEntity? = roomDatabase?.getSettingDao()?.getSettingsById(id)
            SettingMapper.mapSettingModel(settings)
        }
    }
}