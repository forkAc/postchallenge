package com.example.postchallenge.ui.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import io.reactivex.Observable

abstract class BaseViewFragment<T : BaseContract.Presenter> : BaseFragment(), BaseContract.View {

  private lateinit var presenter: T

  protected abstract fun getPresenter(): T

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    presenter = getPresenter()
    presenter.setInitialState(savedInstanceState ?: arguments)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    presenter.setInitialState(savedInstanceState)
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    presenter.let {
      val state = it.getCurrentState()
      outState.putAll(state)
    }
  }

  override fun onStart() {
    super.onStart()
    presenter.start()
  }

  override fun onResume() {
    super.onResume()
    presenter.resume()
  }

  override fun onPause() {
    presenter.pause()
    super.onPause()
  }

  override fun onStop() {
    presenter.stop()
    super.onStop()
  }

  override fun isInternetOn(): Boolean {
    val connectivityManager = context!!.getSystemService(
      Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
  }

}