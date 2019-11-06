package com.mezmeraiz.blank.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
        val pagedList: LiveData<PagedList<T>>,
        val loadInitialState: LiveData<NetworkState>,
        val loadMoreState: LiveData<NetworkState>?,
        val refresh: () -> Unit)