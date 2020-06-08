package com.mellobit.spop.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mellobit.spop.databinding.TrackListItemBinding

class TrackListAdapter : RecyclerView.Adapter<TrackViewHolder>() {

    var tracks: List<Track> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TrackListItemBinding.inflate(inflater, parent, false)
        return TrackViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = tracks[position]
        holder.bind(track)
    }
}

class TrackViewHolder(private val binding: TrackListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    lateinit var track: Track

    fun bind(track: Track) {
        this.track = track
        binding.artistNameLabel.text = track.artist
        binding.trackNameLabel.text = track.name

        Glide.with(binding.coverArtImageView)
            .load(Uri.parse(track.imgUrl))
            .into(binding.coverArtImageView)
    }
}