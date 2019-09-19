package com.example.postchallenge.ui.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.postchallenge.PostApplication
import io.reactivex.Observable

abstract class BaseActivity : AppCompatActivity(), BaseContract.View {

  open val layout: Int = -1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (application as PostApplication).netComponent.inject(this)
    setContentView(layout)
  }

  override fun isInternetOn(): Boolean {
    val connectivityManager = this.getSystemService(
      Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
  }

}