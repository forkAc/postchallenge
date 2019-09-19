package com.example.postchallenge.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "comment")
data class Comment(
  @ColumnInfo(name = "postId") var postId: Int = -1,
  @PrimaryKey @ColumnInfo(name = "id") var id: Int = -1,
  @ColumnInfo(name = "name") var name: String = "",
  @ColumnInfo(name = "email") var email: String = "",
  @ColumnInfo(name = "body") var body: String = ""
) : Parcelable