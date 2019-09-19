package com.example.postchallenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postchallenge.data.model.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

  @Query("SELECT * FROM user WHERE id = :id")
  fun getUser(id: Int): Single<User>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertUser(user: User)
}