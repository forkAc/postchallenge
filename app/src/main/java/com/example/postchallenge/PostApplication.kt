package com.example.postchallenge

import android.app.Application
import com.example.postchallenge.di.ApplicationModule
import com.example.postchallenge.di.DaggerMainInjectorComponent
import com.example.postchallenge.di.MainInjectorComponent
import com.example.postchallenge.di.MainInjectorModule
import com.example.postchallenge.di.NetworkModule

class PostApplication : Application() {

  lateinit var netComponent: MainInjectorComponent
    private set

  override fun onCreate() {
    super.onCreate()

    netComponent = DaggerMainInjectorComponent.builder()
      .applicationModule(ApplicationModule(this))
      .networkModule(NetworkModule())
      .mainInjectorModule(MainInjectorModule())
      .build()

    netComponent.inject(this)
  }
}