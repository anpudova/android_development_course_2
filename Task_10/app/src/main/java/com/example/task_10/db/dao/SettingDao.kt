package com.example.task_10.db.dao

import androidx.room.*
import com.example.task_10.db.entity.SettingEntity
import com.example.task_10.db.model.SettingModel

@Dao
interface SettingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createSettings(user: SettingEntity)

    @Update(entity = SettingEntity::class)
    suspend fun updateSettingData(setting: SettingEntity)

    @Query("SELECT * FROM settings WHERE id_user = :id")
    suspend fun getSettingsById(id: Int): SettingEntity?
}