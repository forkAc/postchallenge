package com.example.postchallenge

import android.os.Bundle
import com.example.postchallenge.ui.base.BaseActivity
import com.example.postchallenge.ui.postlist.PostListFragment
import com.example.postchallenge.utils.extensions.addFragment

class HomeActivity : BaseActivity() {

  override val layout: Int = R.layout.activity_main

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    openAllPosts()
  }

  private fun openAllPosts() {
    addFragment(
      fragment = PostListFragment.newInstance(),
      containerViewId = R.id.nav_host_fragment
    )
  }

}
