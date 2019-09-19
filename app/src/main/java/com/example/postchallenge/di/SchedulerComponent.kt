package com.example.postchallenge.di

import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Named

@Component(modules = [(SchedulerModule::class)])
interface SchedulerComponent {

  @Named(SchedulerModule.VIEW_SCHEDULER)
  fun ioScheduler(): Scheduler

  @Named(SchedulerModule.IO_SCHEDULER)
  fun viewScheduler(): Scheduler

  @Named(SchedulerModule.COMPUTATION_SCHEDULER)
  fun computationScheduler(): Scheduler
}