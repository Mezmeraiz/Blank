package com.mezmeraiz.blank.repository.paging

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.Config
import androidx.paging.DataSource
import androidx.paging.toLiveData
import com.mezmeraiz.blank.model.Listing
import com.mezmeraiz.blank.common.NetManager
import com.mezmeraiz.blank.model.NetworkState
import com.mezmeraiz.blank.model.User
import java.util.concurrent.Executors
import javax.inject.Inject

class PagingRepository @Inject constructor(private val netManager: NetManager,
                                           private val localSourceFactory: DataSource.Factory<Int, User>,
                                           private val remoteSourceFactory: PagingSourceFactory) {

    var result = MutableLiveData<Listing<User>>()

    init {
        if (netManager.isConnectedToInternet){
            result.postValue(getRemoteListing())
        }else{
            result.postValue(getLocalListing())
        }
    }

    private fun getRemoteListing(): Listing<User> {

        val pagedListLiveData = remoteSourceFactory.toLiveData(
            config = Config(
                pageSize = 20,
                initialLoadSizeHint = 20,
                enablePlaceholders = false
            ),
            fetchExecutor = Executors.newSingleThreadExecutor()
        )

        val refreshState = switchMap(remoteSourceFactory.sourceLiveData){
            it.initialLoadState
        }

        val loadMoreState = switchMap(remoteSourceFactory.sourceLiveData){
            it.loadMoreState
        }

        return Listing(
            pagedList = pagedListLiveData,
            loadInitialState = refreshState,
            loadMoreState = loadMoreState,
            refresh = {
                remoteSourceFactory.sourceLiveData.value?.invalidate()

            })
    }

    private fun getLocalListing(): Listing<User> {

        val pagedListLiveData = localSourceFactory.toLiveData(
            config = Config(
                pageSize = 10,
                initialLoadSizeHint = 30,
                enablePlaceholders = false
            ),
            fetchExecutor = ArchTaskExecutor.getIOThreadExecutor()
        )

        val refreshState = MutableLiveData<NetworkState>()

        return Listing(
            pagedList = pagedListLiveData,
            loadInitialState = refreshState,
            loadMoreState = null,
            refresh = {
                if (netManager.isConnectedToInternet) {
                    result.postValue(getRemoteListing())
                } else {
                    refreshState.postValue(NetworkState.LOADED)
                }
            })
    }

}