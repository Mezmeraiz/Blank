package com.mezmeraiz.blank.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class RootViewModel: ViewModel() {

    val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}