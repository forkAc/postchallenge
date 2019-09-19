package com.example.postchallenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postchallenge.data.model.Post
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PostDao {

  @Query("SELECT * FROM post")
  fun getAllPosts(): Single<List<Post>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPost(vararg post: Post)

}