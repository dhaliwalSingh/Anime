package com.example.anime.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anime.AnimeDetail
import com.example.anime.data.Anime
import com.example.anime.data.AnimeRepository
import com.example.anime.databinding.AnimeCardBinding
import com.squareup.picasso.Picasso

class AnimeAdapter(
    private val context: Context,
    private val animeRepository: AnimeRepository,
) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    private var animeList: List<Anime> = emptyList()

    inner class AnimeViewHolder(private val binding: AnimeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val anime = animeList[position]
                    val intent = Intent(context, AnimeDetail::class.java)
                    intent.putExtra("mal_Id", anime.malId) // Ensure the key is "mal_Id"
                    context.startActivity(intent)
                }
            }
        }

        fun bind(anime: Anime) {
            binding.animeTitle.text = anime.title
            binding.animeEp.text = anime.episodes.toString()
            binding.animeType.text = anime.type
            // Load the image using Picasso
            Picasso.get().load(anime.imageURL).into(binding.animeImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AnimeCardBinding.inflate(inflater, parent, false)
        return AnimeViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return animeList.size
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]
        holder.bind(anime)
    }

    fun setAnimeList(animes: List<Anime>) {
        animeList = animes
        notifyDataSetChanged()
    }

}
