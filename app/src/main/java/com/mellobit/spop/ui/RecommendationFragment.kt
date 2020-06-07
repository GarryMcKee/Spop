package com.mellobit.spop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mellobit.spop.Injectable
import com.mellobit.spop.databinding.FragmentRecommendationBinding
import javax.inject.Inject

class RecommendationFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentRecommendationBinding

    val recommendationViewModel: RecommendationViewModel by viewModels { viewModelFactory }
    val adapter = TrackListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecommendationBinding.inflate(inflater)
        binding.tracklist.layoutManager = LinearLayoutManager(requireContext())
        binding.tracklist.adapter = adapter

        recommendationViewModel.trackList.observe(viewLifecycleOwner, Observer {
            adapter.tracks = it
            adapter.notifyDataSetChanged()
        })

        recommendationViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })

        recommendationViewModel.getTracks()

        return binding.root
    }
}