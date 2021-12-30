package com.example.musicassignment2.adaptor

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.musicassignment2.R
import com.example.musicassignment2.model.pop.PopMusic
import com.example.musicassignment2.presenters.PopPresenter
import com.squareup.picasso.Picasso

class PopAdapter(
    private val presenter: PopPresenter,
    private val musicList: MutableList<PopMusic> = mutableListOf()
): RecyclerView.Adapter<MusicViewHolder>() {


    fun updateSongsRV(newMusic: MutableList<PopMusic>){
        musicList.clear()
        musicList.addAll(newMusic)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val songView = LayoutInflater.from(parent.context).inflate(R.layout.music_list,parent,false)
        return MusicViewHolder(songView)
    }



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.nameText.text=musicList[position].collectionName
        holder.artistText.text=musicList[position].artistName
        holder.priceText.text=musicList[position].trackPrice.toString()+" "+presenter.context.getString(
            R.string.priceCurrency)
        Picasso.get().load(musicList[position].artworkUrl60).resize(130,130).into(holder.songImage)
        holder.itemView.setOnClickListener{
            presenter.checkNetworkConnection()
            musicList[position].previewUrl?.let { it1 -> presenter.playPreview(it1) }
        }
    }



    override fun getItemCount(): Int = musicList.size
}