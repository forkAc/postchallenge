package com.example.postchallenge.ui.postlist.adapter

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.postchallenge.R

class PostListEmptyItemViewHolder(
  itemView: View
) : RecyclerView.ViewHolder(itemView) {
  companion object {
    @LayoutRes const val LAYOUT =  R.layout.post_list_empty_item
  }
}