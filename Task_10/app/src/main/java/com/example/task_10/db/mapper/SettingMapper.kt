package com.example.task_10.db.mapper

import com.example.task_10.db.entity.SettingEntity
import com.example.task_10.db.entity.UserEntity
import com.example.task_10.db.model.SettingModel
import com.example.task_10.db.model.UserModel

object SettingMapper {

    fun mapSettingEntity(settings: SettingModel): SettingEntity {
        with(settings) {
            return SettingEntity(
                idUser, settingOne, settingTwo, settingThree
            )
        }
    }

    fun mapSettingModel(settings: SettingEntity?): SettingModel? {
        if (settings != null) {
            with(settings) {
                return SettingModel(
                    idUser, settingOne, settingTwo, settingThree
                )
            }
        } else {
            return null
        }
    }
}