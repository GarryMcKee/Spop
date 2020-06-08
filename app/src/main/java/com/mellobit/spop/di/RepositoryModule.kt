package com.mellobit.spop.di

import com.mellobit.spop.data.AuthenticationRepository
import com.mellobit.spop.data.AuthenticationRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthenticationRepository(authenticationRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository
}