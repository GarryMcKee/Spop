package com.mellobit.spop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mellobit.spop.util.Event
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.authentication.LoginActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val authenticationViewModel: AuthenticationViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        authenticationViewModel.checkAuthenticated()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        authenticationViewModel.attemptAuthentication.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                spotifyAuth()
            }
        })

        return super.onCreateView(name, context, attrs)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private fun spotifyAuth() {
        // Request code will be used to verify if result comes from the login activity. Can be set to any integer.
        val requestCode = 1138
        val redirectUri = "spop://callback"
        val clientId = "3c111ba9afb74477a09347b0b62da582"

        val builder: AuthenticationRequest.Builder = AuthenticationRequest.Builder(
            clientId,
            AuthenticationResponse.Type.TOKEN,
            redirectUri
        )

        builder.setScopes(arrayOf("streaming"))
        val request: AuthenticationRequest = builder.build()

        AuthenticationClient.openLoginActivity(this, requestCode, request)

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        intent: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == LoginActivity.REQUEST_CODE) {
            val response =
                AuthenticationClient.getResponse(resultCode, intent)
            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> {
                    authenticationViewModel.saveAuthToken(response.accessToken)
                }
                AuthenticationResponse.Type.ERROR -> {
                    authenticationViewModel.authError.value = Event(response.error)
                }
                else -> {
                    Timber.e("Unexpected error authenticating Spotify, this should not happen")
                }
            }
        }
    }
}