package com.mezmeraiz.blank.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mezmeraiz.blank.model.User
import javax.inject.Provider

class PagingSourceFactory(private val sourceProvider: Provider<PagedDataSource>) : DataSource.Factory<Int, User>(){

    val sourceLiveData = MutableLiveData<PagedDataSource>()

    override fun create(): DataSource<Int, User> {
        var source = sourceProvider.get()
        sourceLiveData.postValue(source)
        return source
    }

}
