package com.example.postchallenge.ui.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import io.reactivex.Observable

abstract class BaseFragment : Fragment(), BaseContract.View {

  @LayoutRes
  open val layout: Int = -1

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(layout, container, false)
  }

  override fun isInternetOn(): Boolean {
    val connectivityManager = context!!.getSystemService(
      Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
  }

}