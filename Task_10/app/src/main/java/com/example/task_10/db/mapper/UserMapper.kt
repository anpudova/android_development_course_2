package com.example.task_10.db.mapper

import com.example.task_10.db.entity.UserEntity
import com.example.task_10.db.model.UserModel
import com.example.task_10.db.model.UserUpdateModel

object UserMapper {

    fun mapUserEntity(user: UserModel): UserEntity {
        with(user) {
            return UserEntity(
                id, username, password
            )
        }
    }

    fun mapUserModel(user: UserEntity?): UserModel? {
        if (user != null) {
            with(user) {
                return UserModel(
                    id, username, password
                )
            }
        } else {
            return null
        }
    }
}