package com.example.postchallenge.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user")
data class User(
  @PrimaryKey @ColumnInfo(name = "id") var id: Int = -1,
  @ColumnInfo(name = "name") var name: String = "",
  @ColumnInfo(name = "userName") var username: String = ""
) : Parcelable