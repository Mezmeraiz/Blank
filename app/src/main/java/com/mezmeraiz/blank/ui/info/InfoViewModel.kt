package com.mezmeraiz.blank.ui.info

import com.mezmeraiz.blank.model.Resource
import com.mezmeraiz.blank.common.RootViewModel
import com.mezmeraiz.blank.common.SingleLiveEvent
import com.mezmeraiz.blank.model.User
import com.mezmeraiz.blank.repository.info.InfoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InfoViewModel @Inject constructor(private val repository: InfoRepository): RootViewModel() {

    fun insertUser(user: User): SingleLiveEvent<Resource<Long>>{
        user.saved = true
        var insert = SingleLiveEvent<Resource<Long>>()
        disposables.add(repository.insertUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                insert.value = Resource.success(it)
            },{
                insert.value = Resource.error(it.message)
            }))
        return insert
    }

}


