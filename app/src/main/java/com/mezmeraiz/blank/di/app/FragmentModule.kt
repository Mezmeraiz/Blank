package com.mezmeraiz.blank.di.app

import com.mezmeraiz.blank.ui.info.InfoFragment
import com.mezmeraiz.blank.ui.network.NetworkFragment
import com.mezmeraiz.blank.ui.paging.PagingFragment
import com.mezmeraiz.blank.ui.room.RoomFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun pagingFragmentInjector(): PagingFragment

    @ContributesAndroidInjector
    fun networkFragmentInjector(): NetworkFragment

    @ContributesAndroidInjector
    fun roomFragmentInjector(): RoomFragment

    @ContributesAndroidInjector
    fun infoFragmentInjector(): InfoFragment

}