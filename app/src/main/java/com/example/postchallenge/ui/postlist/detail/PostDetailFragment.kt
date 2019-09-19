package com.example.postchallenge.ui.postlist.detail

import android.os.Bundle
import android.view.View
import com.example.postchallenge.PostApplication
import com.example.postchallenge.R
import com.example.postchallenge.data.PostRepository
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.service.PostService
import com.example.postchallenge.ui.base.BaseViewFragment
import kotlinx.android.synthetic.main.fragment_post_detail.indeterminateBar
import kotlinx.android.synthetic.main.fragment_post_detail.post_detail_item_author
import kotlinx.android.synthetic.main.fragment_post_detail.post_detail_item_description
import kotlinx.android.synthetic.main.fragment_post_detail.post_detail_item_number_of_comments
import kotlinx.android.synthetic.main.fragment_post_detail.post_detail_parent

class PostDetailFragment : BaseViewFragment<PostDetailPresenter>(), PostDetailContract.View {

  override val layout: Int = LAYOUT

  override fun getPresenter(): PostDetailPresenter {
    val parentActivity = activity!!
    val app = (parentActivity.application as PostApplication)

    val postService = PostService()
    app.netComponent.inject(postService)

    val repository = PostRepository()
    app.netComponent.inject(repository)

    val presenter = PostDetailPresenter(
      view = this,
      postService = postService,
      repository = repository
    )
    app.netComponent.inject(presenter)
    return presenter
  }

  override fun showParent() {
    post_detail_parent.visibility = View.VISIBLE
  }

  override fun hideParent() {
    post_detail_parent.visibility = View.GONE
  }

  override fun hideProgressBar() {
    indeterminateBar.visibility = View.INVISIBLE
  }

  override fun showProgressBar() {
    indeterminateBar.visibility = View.VISIBLE
  }

  override fun setName(name: String) {
    post_detail_item_author.text = name
  }

  override fun setNumberOfComments(number: Int) {
    post_detail_item_number_of_comments.text = number.toString()
  }

  override fun setDescription(description: String) {
    post_detail_item_description.text = description
  }

  companion object {
    private const val LAYOUT = R.layout.fragment_post_detail

    fun newInstance(post: Post): PostDetailFragment {
      val fragment = PostDetailFragment()
      val bundle = Bundle()
      bundle.putParcelable(PostDetailContract.EXTRA_POST_ITEM, post)
      fragment.arguments = bundle
      return fragment
    }
  }
}