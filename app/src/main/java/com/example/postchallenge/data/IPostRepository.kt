package com.example.postchallenge.data

import com.example.postchallenge.data.model.Comment
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.data.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface IPostRepository {
  fun getAllPosts(): Single<List<Post>>
  fun insertOrUpdatePost(post: Post)

  fun getUser(id: Int): Single<User>
  fun insertOrUpdateUser(user: User)

  fun getAllCommentsInPost(id: Int): Single<Int>
  fun insertOrUpdateComment(comment: Comment)
}