package com.mellobit.spop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mellobit.spop.databinding.FragmentRecommendationBinding
import javax.inject.Inject

class RecommendationFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentRecommendationBinding

    val recommendationViewModel: RecommendationViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecommendationBinding.inflate(inflater)

        recommendationViewModel.messageLiveData.observe(viewLifecycleOwner, Observer {
            binding.welcomeMessage.text = it
        })

        recommendationViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })

        recommendationViewModel.getMessage()

        return binding.root
    }
}