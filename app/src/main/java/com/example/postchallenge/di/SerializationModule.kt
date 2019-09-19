package com.example.postchallenge.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SerializationModule {

  @ApplicationScope
  @Provides
  @Named(GSON_WITH_NULLS)
  fun provideGsonWithNullSerialization(): Gson {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.serializeNulls()
    return gsonBuilder.create()
  }

  @ApplicationScope
  @Provides
  @Named(GSON_SIMPLE)
  fun provideSimpleGson(): Gson {
    return GsonBuilder().create()
  }

  companion object {
    const val GSON_WITH_NULLS = "gson_with_nulls"
    const val GSON_SIMPLE = "gson_simple"
  }
}
