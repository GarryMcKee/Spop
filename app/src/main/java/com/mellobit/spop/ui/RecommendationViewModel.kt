package com.mellobit.spop.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mellobit.spop.di.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RecommendationViewModel @Inject constructor(val schedulerProvider: SchedulerProvider) :
    ViewModel() {
    val trackList = MutableLiveData<List<Track>>()
    val errorLiveData = MutableLiveData<Throwable>()

    private val compositeDisposable = CompositeDisposable()

    fun getTracks() {
        compositeDisposable.add(
            Single.just(
                testTracks()
            ).subscribe({
                trackList.value = it
            }, {
                errorLiveData.value = it
            })
        )
    }

    private fun testTracks(): List<Track> {
        return listOf(
            Track(
                "Breathe",
                "Pink Floyd",
                "Dark Side of the Moon",
                "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png"
            ),
            Track(
                "Idioteque",
                "Radiohead",
                "Kid A",
                "https://upload.wikimedia.org/wikipedia/en/b/b5/Radiohead.kida.albumart.jpg"
            ),
            Track(
                "Bulletproof Cupid",
                "Placebo",
                "Sleeping With Ghosts",
                "https://upload.wikimedia.org/wikipedia/en/1/1e/Sleeping_with_ghosts.jpg"
            ),
            Track(
                "Breathe",
                "Pink Floyd",
                "Dark Side of the Moon",
                "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png"
            ),
            Track(
                "Idioteque",
                "Radiohead",
                "Kid A",
                "https://upload.wikimedia.org/wikipedia/en/b/b5/Radiohead.kida.albumart.jpg"
            ),
            Track(
                "Bulletproof Cupid",
                "Placebo",
                "Sleeping With Ghosts",
                "https://upload.wikimedia.org/wikipedia/en/1/1e/Sleeping_with_ghosts.jpg"
            ),
            Track(
                "Breathe",
                "Pink Floyd",
                "Dark Side of the Moon",
                "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png"
            ),
            Track(
                "Idioteque",
                "Radiohead",
                "Kid A",
                "https://upload.wikimedia.org/wikipedia/en/b/b5/Radiohead.kida.albumart.jpg"
            ),
            Track(
                "Bulletproof Cupid",
                "Placebo",
                "Sleeping With Ghosts",
                "https://upload.wikimedia.org/wikipedia/en/1/1e/Sleeping_with_ghosts.jpg"
            )
        )
    }
}