package com.example.postchallenge.di

import com.google.gson.Gson
import dagger.Component
import javax.inject.Named

@ApplicationScope
@Component(modules = [(SerializationModule::class)])
interface SerializationComponent {

  @Named(SerializationModule.GSON_WITH_NULLS)
  fun provideGsonWithNullSerialization(): Gson

  @Named(SerializationModule.GSON_SIMPLE)
  fun provideSimpleGson(): Gson
}
