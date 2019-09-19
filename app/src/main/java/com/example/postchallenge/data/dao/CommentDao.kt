package com.example.postchallenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postchallenge.data.model.Comment
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CommentDao {

  @Query("SELECT COUNT(postId) FROM comment WHERE postId = :id")
  fun getAllCommentsInPost(id: Int): Single<Int>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertComment(comment: Comment)

}