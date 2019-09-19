package com.example.postchallenge.ui.postlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.postchallenge.data.model.Post
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class PostListAdapter(
  private val viewDetachEvent: Observable<Any>
) : RecyclerView.Adapter<ViewHolder>() {

  private val postSelectedPublisher: PublishSubject<Post> = PublishSubject.create()
  private val viewScheduler: Scheduler = AndroidSchedulers.mainThread()

  private var postList = mutableListOf<Post>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    when (viewType) {
      LIST_TYPE_ITEM -> {
        createPostListItemViewHolder(parent)
      }
      else -> {
        createEmptyViewHolder(parent)
      }
    }

  private fun createPostListItemViewHolder(parent: ViewGroup): PostListItemViewHolder {
    val view = LayoutInflater
      .from(parent.context)
      .inflate(PostListItemViewHolder.LAYOUT, parent, false)
    return PostListItemViewHolder(
      itemView = view,
      postSelectedPublisher = postSelectedPublisher,
      viewDetachEvent = viewDetachEvent,
      viewScheduler = viewScheduler
    )
  }

  private fun createEmptyViewHolder(parent: ViewGroup): PostListEmptyItemViewHolder{
    val view = LayoutInflater
      .from(parent.context)
      .inflate(PostListEmptyItemViewHolder.LAYOUT, parent, false)
    return PostListEmptyItemViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    when (holder.itemViewType) {
      LIST_TYPE_ITEM -> {
        val viewHolder = holder as PostListItemViewHolder
        viewHolder.onBind(getCodeForPosition(position))
      }
    }
  }

  private fun getCodeForPosition(position: Int): Post =  postList[position]

  override fun getItemViewType(position: Int): Int = LIST_TYPE_ITEM

  override fun getItemCount(): Int = postList.size

  fun updatePostList(postList: List<Post>) {
    this.postList.clear()
    this.postList.addAll(postList)
    notifyDataSetChanged()
  }

  fun postItemSelected(): Observable<Post> = postSelectedPublisher

  companion object {
    private const val LIST_TYPE_ITEM = 0
  }
}