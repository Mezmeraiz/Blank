package com.mezmeraiz.blank.repository.paging

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mezmeraiz.blank.model.NetworkState
import com.mezmeraiz.blank.db.UserDao
import com.mezmeraiz.blank.model.User
import com.mezmeraiz.blank.network.UserService

class PagedDataSource (private val service: UserService, private val userDao: UserDao) : PageKeyedDataSource<Int, User>() {

    val initialLoadState = MutableLiveData<NetworkState>()

    val loadMoreState = MutableLiveData<NetworkState>()

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        initialLoadState.postValue(NetworkState.LOADING)
        userDao?.delete()
        service.users(1, params.requestedLoadSize).subscribe({
            var items = it.results ?: emptyList<User>()
            userDao?.insert(items)
            callback.onResult(items, null, it.info?.page?.inc())
            initialLoadState.postValue(NetworkState.LOADED)
        },{
            initialLoadState.postValue(NetworkState.error(it.message))
        })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        loadMoreState.postValue(NetworkState.LOADING)
        service.users(params.key, params.requestedLoadSize).subscribe({
            var items = it.results ?: emptyList<User>()
            Thread {userDao?.insert(items)}.start()
            callback.onResult(items, it.info?.page?.inc())
            loadMoreState.postValue(NetworkState.LOADED)
        },{
            loadMoreState.postValue(NetworkState.error(it.message))
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
    }

}