package com.example.postchallenge.utils.extensions

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.postchallenge.ui.base.BaseFragment

fun BaseFragment.replaceFragment(
  fragment: Fragment,
  @IdRes containerViewId: Int,
  TAG: String,
  @AnimatorRes @AnimRes enter: Int,
  @AnimatorRes @AnimRes exit: Int,
  @AnimatorRes @AnimRes popEnter: Int,
  @AnimatorRes @AnimRes popExit: Int
) {
  if (isAdded) {
    val ft = childFragmentManager.beginTransaction()
      .setCustomAnimations(enter, exit, popEnter, popExit)
      .replace(containerViewId, fragment, TAG)
      .addToBackStack(TAG)
    if (!childFragmentManager.isStateSaved) {
      ft.commit()
    }
  }
}
