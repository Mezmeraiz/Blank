package com.mezmeraiz.blank.di

import android.app.Application
import com.mezmeraiz.blank.App
import com.mezmeraiz.blank.di.app.FragmentModule
import com.mezmeraiz.blank.di.app.ViewModelModule
import com.mezmeraiz.blank.di.app.NetworkModule
import com.mezmeraiz.blank.di.paging.PagingModule
import com.mezmeraiz.blank.di.app.RoomModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    FragmentModule::class,
    NetworkModule::class,
    RoomModule::class,
    PagingModule::class])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}