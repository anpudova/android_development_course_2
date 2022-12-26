package com.example.task_10.db.entity

import androidx.room.*

@Entity(
    tableName = "settings",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_user"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class SettingEntity(
    @PrimaryKey @ColumnInfo(name = "id_user") val idUser: Int,
    @ColumnInfo(name = "setting_one") val settingOne: Int,
    @ColumnInfo(name = "setting_two") val settingTwo: Int,
    @ColumnInfo(name = "setting_three") val settingThree: Int
)
