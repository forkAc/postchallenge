package com.example.postchallenge.di

import com.example.postchallenge.PostApplication
import com.example.postchallenge.data.PostRepository
import com.example.postchallenge.service.PostService
import com.example.postchallenge.ui.base.BaseActivity
import com.example.postchallenge.ui.base.BaseFragment
import com.example.postchallenge.ui.postlist.PostListPresenter
import com.example.postchallenge.ui.postlist.detail.PostDetailPresenter
import dagger.Component

@ApplicationScope
@Component(modules = [
  (MainInjectorModule::class), (ApplicationModule::class),
  (NetworkModule::class), (SchedulerModule::class),
  (SerializationModule::class), (DatabaseModule::class)])
interface MainInjectorComponent {

  fun inject(application: PostApplication)

  // base
  fun inject(activity: BaseActivity)

  fun inject(fragment: BaseFragment)

  // services
  fun inject(service: PostService)

  // presenters
  fun inject(presenter: PostListPresenter)

  fun inject(presenter: PostDetailPresenter)

  //repositories
  fun inject(repository: PostRepository)

}