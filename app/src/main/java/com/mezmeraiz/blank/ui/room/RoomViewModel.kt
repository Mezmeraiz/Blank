package com.mezmeraiz.blank.ui.room

import androidx.lifecycle.*
import com.mezmeraiz.blank.model.Resource
import com.mezmeraiz.blank.common.RootViewModel
import com.mezmeraiz.blank.model.User
import com.mezmeraiz.blank.repository.room.RoomRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RoomViewModel @Inject constructor(private val repository: RoomRepository): RootViewModel() {

    var users = MutableLiveData<Resource<List<User>>>()

    @Inject
    fun loadUsers(){
        disposables.add(repository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                users.value = Resource.success(it)
            },{
                users.value = Resource.error(it.message)
            }))
    }

}


