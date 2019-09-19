package com.example.postchallenge.ui.base

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter : BaseContract.Presenter {

  private var onResumeSubscriptions: CompositeDisposable? = null
  private var onStartSubscriptions: CompositeDisposable? = null

  override fun setInitialState(state: Bundle?) {
    // does nothing
  }

  override fun getCurrentState(): Bundle = Bundle()

  override fun start() {
    onStartSubscriptions = CompositeDisposable()
  }

  override fun resume() {
    onResumeSubscriptions = CompositeDisposable()
  }

  override fun pause() {
    if (onResumeSubscriptions != null && !onResumeSubscriptions?.isDisposed!!) {
      onResumeSubscriptions!!.dispose()
      onResumeSubscriptions = null
    }
  }

  override fun stop() {
    if (onStartSubscriptions != null && !onStartSubscriptions?.isDisposed!!) {
      onStartSubscriptions!!.dispose()
      onStartSubscriptions = null
    }
  }

  protected fun addOnResumeSubscription(subscription: Disposable) {
    onResumeSubscriptions?.add(subscription)
  }

  protected fun addOnStartSubscription(subscription: Disposable) {
    onStartSubscriptions?.add(subscription)
  }

}