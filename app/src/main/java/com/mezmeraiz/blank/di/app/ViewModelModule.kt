package com.mezmeraiz.blank.di.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mezmeraiz.blank.di.DaggerViewModelFactory
import com.mezmeraiz.blank.di.ViewModelKey
import com.mezmeraiz.blank.ui.info.InfoViewModel
import com.mezmeraiz.blank.ui.network.NetworkViewModel
import com.mezmeraiz.blank.ui.paging.PagingViewModel
import com.mezmeraiz.blank.ui.room.RoomViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule() {

    @Binds
    @IntoMap
    @ViewModelKey(PagingViewModel::class)
    abstract fun bindsPagingViewModel(pagingViewModel: PagingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NetworkViewModel::class)
    abstract fun bindsNetworkViewModel(networkViewModel: NetworkViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoomViewModel::class)
    abstract fun bindsRoomViewModel(roomViewModel: RoomViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfoViewModel::class)
    abstract fun bindsInfoViewModel(infoViewModel: InfoViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}