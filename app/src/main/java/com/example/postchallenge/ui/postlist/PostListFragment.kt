package com.example.postchallenge.ui.postlist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postchallenge.PostApplication
import com.example.postchallenge.R
import com.example.postchallenge.data.IPostRepository
import com.example.postchallenge.data.PostRepository
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.service.PostService
import com.example.postchallenge.ui.base.BaseActivity
import com.example.postchallenge.ui.base.BaseViewFragment
import com.example.postchallenge.ui.postlist.adapter.PostListAdapter
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_post_list.*
import java.util.concurrent.TimeUnit

class PostListFragment : BaseViewFragment<PostListPresenter>(), PostListContract.View {

  override val layout: Int = R.layout.fragment_post_list

  override fun getPresenter(): PostListPresenter {
    val parentActivity = activity!!
    val app = (parentActivity.application as PostApplication)

    val postService = PostService()
    app.netComponent.inject(postService)

    val navigator = PostListNavigator(
      parentActivity = parentActivity as BaseActivity
    )

    val repository = PostRepository()
    app.netComponent.inject(repository)

    val presenter = PostListPresenter(
      view = this,
      navigator = navigator,
      postService = postService,
      repository = repository
    )
    app.netComponent.inject(presenter)
    return presenter
  }

  private lateinit var adapter: PostListAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupAdapter(view)
  }

  private fun setupAdapter(view: View) {
    adapter = PostListAdapter(
      viewDetachEvent = RxView.detaches(view)
    )
    val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    post_recycler_view.layoutManager = layoutManager
    post_recycler_view.adapter = adapter
  }

  override fun showParent() {
    post_recycler_view.visibility = View.VISIBLE
  }

  override fun hideParent() {
    post_recycler_view.visibility = View.GONE
  }

  override fun hideProgressBar() {
    indeterminateBar.visibility = View.INVISIBLE
  }

  override fun showProgressBar() {
    indeterminateBar.visibility = View.VISIBLE
  }

  override fun setPostList(postList: List<Post>) {
    changeViewFlipper(PostListContract.FLIPPER_DISPLAY)
    adapter.updatePostList(postList)
  }

  override fun setEmptyState() {
    changeViewFlipper(PostListContract.FLIPPER_EMPTY)
  }

  private fun changeViewFlipper(state: Int) {
    flipper.displayedChild = state
  }

  override fun postItemSelected(): Observable<Post> = adapter.postItemSelected()

  override fun tapToRetry(): Observable<Any> =
    RxView.clicks(refreshImg).throttleFirst(300L, TimeUnit.MILLISECONDS)

  companion object {
    fun newInstance(): PostListFragment = PostListFragment()
  }

}