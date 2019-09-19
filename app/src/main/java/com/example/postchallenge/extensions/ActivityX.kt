package com.example.postchallenge.utils.extensions

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.postchallenge.ui.base.BaseActivity

fun BaseActivity.addFragment(
  fragment: Fragment,
  @IdRes containerViewId: Int
) {
  val ft = supportFragmentManager.beginTransaction()
    .replace(containerViewId, fragment)
  if (!supportFragmentManager.isStateSaved) {
    ft.commit()
  }
}

fun BaseActivity.replaceFragment(
  fragment: Fragment,
  @IdRes containerViewId: Int,
  tag: String
) {
  val ft = supportFragmentManager.beginTransaction()
    .replace(containerViewId, fragment, tag)
    .addToBackStack(tag)
  if (!supportFragmentManager.isStateSaved) {
    ft.commit()
  }
}