package com.example.postchallenge.ui.postlist.detail

import com.example.postchallenge.ui.base.BaseContract

class PostDetailContract {

  interface View : BaseContract.View {
    fun hideProgressBar()
    fun showProgressBar()
    fun showParent()
    fun hideParent()

    fun setName(name: String)
    fun setNumberOfComments(number: Int)
    fun setDescription(description: String)
  }

  interface Presenter {
    fun handleGetPostDetail()
  }

  interface Navigator

  companion object {
    const val EXTRA_POST_ITEM = "extra_post_item"
  }

}