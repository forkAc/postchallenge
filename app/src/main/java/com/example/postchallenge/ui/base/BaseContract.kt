package com.example.postchallenge.ui.base

import android.os.Bundle
import io.reactivex.Observable

interface BaseContract {

  interface Presenter {
    fun start()
    fun resume()
    fun pause()
    fun stop()

    fun getCurrentState(): Bundle
    fun setInitialState(state: Bundle?)
  }

  interface View {
    fun isInternetOn(): Boolean
  }

}