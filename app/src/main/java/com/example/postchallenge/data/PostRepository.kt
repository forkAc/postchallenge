package com.example.postchallenge.data

import com.example.postchallenge.data.dao.CommentDao
import com.example.postchallenge.data.dao.PostDao
import com.example.postchallenge.data.dao.UserDao
import com.example.postchallenge.data.model.Comment
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.data.model.User
import io.reactivex.Single
import javax.inject.Inject

class PostRepository : IPostRepository {

  @Inject
  lateinit var postDao: PostDao

  @Inject
  lateinit var userDao: UserDao

  @Inject
  lateinit var commentDao: CommentDao

  override fun getAllPosts(): Single<List<Post>> = postDao.getAllPosts()

  override fun getUser(id: Int): Single<User> = userDao.getUser(id)

  override fun getAllCommentsInPost(id: Int): Single<Int> = commentDao.getAllCommentsInPost(id)

  override fun insertOrUpdatePost(post: Post) = postDao.insertPost(post)

  override fun insertOrUpdateUser(user: User) = userDao.insertUser(user)

  override fun insertOrUpdateComment(comment: Comment) = commentDao.insertComment(comment)

}