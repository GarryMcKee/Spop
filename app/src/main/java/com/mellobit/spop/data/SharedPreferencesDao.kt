package com.mellobit.spop.data

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SharedPreferencesDao @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveAuthToken(authToken: String): Completable {
        return Completable.fromAction {
            sharedPreferences
                .edit()
                .putString(AUTH_TOKEN_KEY, authToken)
                .commit()
        }
    }

    fun observeAuthToken(): Single<String> {
        val authToken = sharedPreferences.getString(AUTH_TOKEN_KEY, "")
        return Single.just(authToken)
    }

    companion object {
        const val AUTH_TOKEN_KEY = "authTokenKey"
    }
}