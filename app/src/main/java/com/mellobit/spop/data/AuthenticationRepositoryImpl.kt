package com.mellobit.spop.data

import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val sharedPreferencesDao: SharedPreferencesDao) :
    AuthenticationRepository {
    override fun saveAuthToken(authToken: String): Completable {
        return sharedPreferencesDao.saveAuthToken(authToken)
    }

    override fun observeAuthToken(): Single<String> {
        return sharedPreferencesDao.observeAuthToken()
    }
}