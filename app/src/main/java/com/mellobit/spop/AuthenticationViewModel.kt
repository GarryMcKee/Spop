package com.mellobit.spop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mellobit.spop.data.AuthenticationRepository
import com.mellobit.spop.di.SchedulerProvider
import com.mellobit.spop.util.Event
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val schedulerProvider: SchedulerProvider
) :
    ViewModel() {
    val authError = MutableLiveData<Event<String>>()
    val attemptAuthentication = MutableLiveData<Event<Boolean>>()
    val isAuthenticated = MutableLiveData<Event<Boolean>>()

    private val compositeDisposable = CompositeDisposable()

    fun checkAuthenticated() {
        compositeDisposable.add(
            authenticationRepository.observeAuthToken()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    if (it.isEmpty()) {
                        attemptAuthentication.value = Event(true)
                    }
                }, {
                    authError.value = Event(it.message.toString())
                })
        )

    }

    fun saveAuthToken(authToken: String) {
        compositeDisposable.add(
            authenticationRepository
                .saveAuthToken(authToken)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    isAuthenticated.value = Event(true)
                }, {
                    Timber.e("Error saving auth token: ${it.message}")
                })
        )
    }
}