package com.example.postchallenge.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.postchallenge.data.dao.CommentDao
import com.example.postchallenge.data.dao.PostDao
import com.example.postchallenge.data.dao.UserDao
import com.example.postchallenge.data.model.Comment
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.data.model.User

@Database(
  entities = [User::class, Comment::class, Post::class],
  version = PostDatabase.DB_VERSION,
  exportSchema = false
)
abstract class PostDatabase : RoomDatabase() {

  abstract fun getPostDao(): PostDao

  abstract fun getUserDao(): UserDao

  abstract fun getCommentDao(): CommentDao

  companion object {
    internal const val DB_VERSION = 1
  }
}