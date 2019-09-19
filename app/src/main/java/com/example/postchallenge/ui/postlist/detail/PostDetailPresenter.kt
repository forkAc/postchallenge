package com.example.postchallenge.ui.postlist.detail

import android.os.Bundle
import android.util.Log
import com.example.postchallenge.data.PostRepository
import com.example.postchallenge.data.model.Comment
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.data.model.User
import com.example.postchallenge.di.SchedulerModule
import com.example.postchallenge.service.PostService
import com.example.postchallenge.ui.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class PostDetailPresenter constructor(
  private val view: PostDetailContract.View,
  private val postService: PostService,
  private val repository: PostRepository
) : BasePresenter(), PostDetailContract.Presenter {

  @Inject
  @field:Named(SchedulerModule.VIEW_SCHEDULER)
  lateinit var viewScheduler: Scheduler

  @Inject
  @field:Named(SchedulerModule.IO_SCHEDULER)
  lateinit var ioScheduler: Scheduler

  private lateinit var post: Post

  override fun setInitialState(state: Bundle?) {
    super.setInitialState(state)
    state?.let {
      if (state.containsKey(PostDetailContract.EXTRA_POST_ITEM))
        post = state.getParcelable(PostDetailContract.EXTRA_POST_ITEM)!!
    }
  }

  override fun resume() {
    super.resume()
    handleGetPostDetail()
  }

  override fun handleGetPostDetail() {
    addOnResumeSubscription(
      Observable.just(0)
        .map { view.isInternetOn() }
        .doOnNext {
          view.hideParent()
          view.showProgressBar()
        }
        .flatMapSingle { hasInternet ->
          if (!hasInternet) {
            Single.zip(
              repository.getUser(post.userId),
              repository.getAllCommentsInPost(post.id),
              BiFunction<User, Int, Pair<String, Int>> { user, numberOfComments ->
                Pair(user.name, numberOfComments)
              }
            )
              .subscribeOn(ioScheduler)
          } else {
            Single.zip(
              getUsers(),
              getComments(),
              BiFunction<List<User>, List<Comment>, Pair<String, Int>> { users, comments ->
                users.forEach { repository.insertOrUpdateUser(it) }
                comments.forEach { repository.insertOrUpdateComment(it) }
                Pair(users.find { it.id == post.userId }!!.name,
                  comments.filter { it.postId == post.id }.size)
              }
            )
              .subscribeOn(ioScheduler)
          }
        }
        .observeOn(viewScheduler)
        .subscribeBy(
          onNext = { (name, numberOfComments) ->
            view.hideProgressBar()
            view.showParent()
            view.setName(name)
            view.setNumberOfComments(numberOfComments)
            view.setDescription(post.body)
          },
          onError = { error ->
            Log.e(error.message, "ERROR")
          }
        )
    )
  }

  private fun getUsers(): Single<List<User>> = postService.getUsers()

  private fun getComments(): Single<List<Comment>> = postService.getComments()
}