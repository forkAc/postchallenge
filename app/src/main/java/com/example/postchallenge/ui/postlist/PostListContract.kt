package com.example.postchallenge.ui.postlist

import com.example.postchallenge.data.model.Post
import com.example.postchallenge.ui.base.BaseContract
import io.reactivex.Observable

interface PostListContract {

  interface View : BaseContract.View {
    fun hideProgressBar()
    fun showProgressBar()
    fun showParent()
    fun hideParent()
    fun setPostList(postList: List<Post>)
    fun setEmptyState()
    fun postItemSelected(): Observable<Post>
    fun tapToRetry(): Observable<Any>
  }

  interface Presenter : BaseContract.Presenter {
    fun handleGetPostList()
    fun handlePostSelected()
    fun handleTapToRetry()
  }

  interface Navigator {
    fun navigateToPostDetail(post: Post)
  }

  companion object {
    const val FLIPPER_DISPLAY = 0
    const val FLIPPER_EMPTY = 1
  }
}