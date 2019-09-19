package com.example.postchallenge.di

import com.example.postchallenge.PostApplication
import dagger.Component

@ApplicationScope
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
  fun inject(application: PostApplication)

  fun application(): PostApplication
}
