package com.mellobit.spop.data

import io.reactivex.Completable
import io.reactivex.Single

interface AuthenticationRepository {
    fun saveAuthToken(authToken: String): Completable

    fun observeAuthToken(): Single<String>
}