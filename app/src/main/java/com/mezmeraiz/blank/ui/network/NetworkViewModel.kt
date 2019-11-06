package com.mezmeraiz.blank.ui.network

import androidx.lifecycle.*
import com.mezmeraiz.blank.model.Resource
import com.mezmeraiz.blank.common.RootViewModel
import com.mezmeraiz.blank.model.User
import com.mezmeraiz.blank.repository.network.NetworkRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NetworkViewModel @Inject constructor(private val repository: NetworkRepository): RootViewModel() {

    var users = MutableLiveData<Resource<MutableList<User>>>()

    @Inject
    fun loadUsers(){
        disposables.add(repository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                users.value = Resource.success(it.results)
            },{
                users.value = Resource.error(it.message)
            }))
    }

}


