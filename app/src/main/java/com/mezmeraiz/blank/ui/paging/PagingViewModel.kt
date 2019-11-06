package com.mezmeraiz.blank.ui.paging

import androidx.lifecycle.Transformations.switchMap
import com.mezmeraiz.blank.common.RootViewModel
import com.mezmeraiz.blank.repository.paging.PagingRepository
import com.mezmeraiz.blank.repository.paging.UserDiffCallback
import javax.inject.Inject

class PagingViewModel @Inject constructor(private val repository: PagingRepository,
                                          val diffCallback: UserDiffCallback): RootViewModel() {

    var pagedList = switchMap(repository.result){
        it.pagedList
    }

    var loadMoreState = switchMap(repository.result){
        it.loadMoreState
    }

    var loadInitialState = switchMap(repository.result){
        it.loadInitialState
    }

    fun refresh() {
        repository.result.value?.refresh?.invoke()
    }

}


