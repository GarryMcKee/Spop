/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mellobit.spop

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mellobit.spop.di.RepositoryModule
import com.mellobit.spop.di.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, RepositoryModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideSpotifyService(): SpotifyService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SpotifyService::class.java)
    }

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    internal fun provideApplication(spopApp: Application): SpopApp {
        return spopApp as SpopApp
    }

    @Provides
    internal fun provideApplicationContext(spopApp: SpopApp): Context {
        return spopApp.applicationContext
    }

    @Provides
    internal fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE)
    }

    companion object {
        const val PREF_FILE_KEY = "spopPrefKey"
    }
}
