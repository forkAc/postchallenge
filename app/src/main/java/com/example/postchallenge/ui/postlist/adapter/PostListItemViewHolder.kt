package com.example.postchallenge.ui.postlist.adapter

import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.postchallenge.R
import com.example.postchallenge.data.model.Post
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.post_list_item.view.post_list_item_body
import kotlinx.android.synthetic.main.post_list_item.view.post_list_item_parent
import kotlinx.android.synthetic.main.post_list_item.view.post_list_item_title

class PostListItemViewHolder constructor(
  itemView: View,
  postSelectedPublisher: PublishSubject<Post>,
  viewDetachEvent: Observable<Any>,
  viewScheduler: Scheduler
) : RecyclerView.ViewHolder(itemView) {

  private lateinit var currentPost: Post

  init {
    RxView.clicks(itemView.post_list_item_parent)
      .takeUntil(viewDetachEvent)
      .observeOn(viewScheduler)
      .retry { error ->
        Log.e(error.message, "ERROR")
        true
      }
      .subscribeBy(
        onNext = { postSelectedPublisher.onNext(currentPost) },
        onError = { error ->         Log.e(error.message, "ERROR") }
      )
  }

  fun onBind(post: Post) {
    this.currentPost = post
    itemView.post_list_item_title.text = post.title
    itemView.post_list_item_body.text = post.body
  }

  companion object {
    @LayoutRes const val LAYOUT = R.layout.post_list_item
  }
}