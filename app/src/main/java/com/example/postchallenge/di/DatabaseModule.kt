package com.example.postchallenge.di

import androidx.room.Room
import com.example.postchallenge.PostApplication
import com.example.postchallenge.data.PostDatabase
import com.example.postchallenge.data.dao.CommentDao
import com.example.postchallenge.data.dao.PostDao
import com.example.postchallenge.data.dao.UserDao
import dagger.Module
import dagger.Provides

@Module
abstract class DatabaseModule {

  @Module
  companion object {

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideDatabase(application: PostApplication): PostDatabase =
      Room.databaseBuilder(application, PostDatabase::class.java, "post-db")
        .build()

    @Provides
    @ApplicationScope
    @JvmStatic
    fun providePostDao(database: PostDatabase): PostDao = database.getPostDao()

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideUserDao(database: PostDatabase): UserDao = database.getUserDao()

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideCommentDao(database: PostDatabase): CommentDao = database.getCommentDao()

  }

}