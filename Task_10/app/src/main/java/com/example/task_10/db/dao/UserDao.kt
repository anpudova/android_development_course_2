package com.example.task_10.db.dao

import androidx.room.*
import com.example.task_10.db.entity.UserEntity
import com.example.task_10.db.model.UserUpdateModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun createUser(user: UserEntity)

    @Update(entity = UserEntity::class)
    suspend fun updateUserData(userDataModel: UserUpdateModel)

    @Query("select * from users where username = :username and password = :password")
    suspend fun getUser(username: String, password: String): UserEntity?

    @Query("select username from users where username = :username")
    suspend fun getUsername(username: String): String?

}