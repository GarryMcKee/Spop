/*
 * Copyright (C) 2018 The Android Open Source Project
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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mellobit.spop.di.SpopViewModelFactory
import com.mellobit.spop.ui.RecommendationViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecommendationViewModel::class)
    abstract fun bindRecommendationViewModel(recommendationViewModel: RecommendationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthenticationViewModel::class)
    abstract fun bindAuthenticationViewModel(authenticationViewModel: AuthenticationViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: SpopViewModelFactory): ViewModelProvider.Factory
}
