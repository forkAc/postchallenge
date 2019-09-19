package com.example.postchallenge.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "post")
@Parcelize
data class Post(
  @ColumnInfo(name = "userId") var userId: Int = -1,
  @PrimaryKey @ColumnInfo(name = "id") var id: Int = -1,
  @ColumnInfo(name = "title") var title: String = "",
  @ColumnInfo(name = "body") var body: String = ""
) : Parcelable