package com.example.postchallenge.service

import com.example.postchallenge.data.model.Comment
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.data.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface PostApi {

  @GET
  fun getPostList(
    @Url url: String
  ): Single<List<Post>>

  @GET
  fun getUsers(
    @Url url: String
  ): Single<List<User>>

  @GET
  fun getComments(
    @Url url: String
  ): Single<List<Comment>>

}