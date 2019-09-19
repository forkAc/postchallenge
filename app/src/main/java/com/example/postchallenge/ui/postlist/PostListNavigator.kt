package com.example.postchallenge.ui.postlist

import com.example.postchallenge.R
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.ui.base.BaseActivity
import com.example.postchallenge.ui.postlist.detail.PostDetailFragment
import com.example.postchallenge.utils.extensions.replaceFragment

class PostListNavigator(
  private val parentActivity: BaseActivity
) : PostListContract.Navigator {

  override fun navigateToPostDetail(post: Post) {
    parentActivity.replaceFragment(
      fragment = PostDetailFragment.newInstance(post),
      containerViewId = R.id.nav_host_fragment,
      tag = PostDetailFragment::class.java.name
    )
  }

}