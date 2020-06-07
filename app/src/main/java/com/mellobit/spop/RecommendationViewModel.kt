package com.mellobit.spop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RecommendationViewModel @Inject constructor(val schedulerProvider: SchedulerProvider) :
    ViewModel() {
    val messageLiveData = MutableLiveData<String>()
    val errorLiveData = MutableLiveData<Throwable>()
    val compositeDisposable = CompositeDisposable()

    fun getMessage() {
        compositeDisposable.add(
            Single.just("Hello Spop!")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    messageLiveData.value = it
                }, {
                    errorLiveData.value = it
                })
        )
    }
}