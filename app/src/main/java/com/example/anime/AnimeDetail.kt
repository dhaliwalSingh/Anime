package com.example.anime

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.anime.data.Anime
import com.example.anime.data.AnimeRepository
import com.example.anime.network.IapiResponse
import com.example.anime.network.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimeDetail : AppCompatActivity() {
    private lateinit var apiService: IapiResponse
    private lateinit var animeRepository: AnimeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)

        apiService = RetrofitInstance.retrofitService
        animeRepository = AnimeRepository(application)

        val malId = intent.getIntExtra("mal_Id", -1)
        Log.d("AnimeDetail", "Received malId: $malId")
        if (malId != -1) {
            // Fetch detailed anime information using malId
            lifecycleScope.launch {
                val anime = fetchAnimeDetail(malId)
                anime?.let {
                    Log.d("AnimeDetail", "Received Anime: $it")
                    updateUI(it)
                } ?: kotlin.run {
                    Log.e("AnimeDetail", "Anime details not available")
                    // Handle error: Anime details not available
                }
            }
        } else {
            Log.e("AnimeDetail", "malId not passed")
            // Handle error: malId not passed
        }

        // Set up click listener for the "Add to Favorite" icon
        val addFavIcon = findViewById<ImageView>(R.id.addFav)
        addFavIcon.setOnClickListener {
            addAnimeToFavorites()
        }
    }

    private suspend fun fetchAnimeDetail(malId: Int): Anime? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAnimeDetail(malId)
                response.data // Return the Anime object from the APIResponse
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
    private fun updateUI(anime: Anime) {
        findViewById<TextView>(R.id.animeTitle).text = anime.title
        findViewById<TextView>(R.id.animeDetailEp).text = anime.episodes.toString()
        findViewById<TextView>(R.id.animeDetailYear).text = anime.year?.toString() ?: "N/A"
        findViewById<TextView>(R.id.animeDetailType).text = anime.type
        findViewById<TextView>(R.id.animeDetailSynopsis).text = anime.synopsis

        // Load the image using Picasso
        val imageView = findViewById<ImageView>(R.id.animePic)
        Picasso.get().load(anime.imageURL).into(imageView)
    }

    private fun addAnimeToFavorites() {
        val malId = intent.getIntExtra("mal_Id", -1)
        if (malId != -1) {
            lifecycleScope.launch {
                try {
                    // Fetch anime details from the API
                    val anime = fetchAnimeDetail(malId)
                    anime?.let {
                        // Insert the anime into favorites
                        animeRepository.insertAnime(it)
                        Toast.makeText(applicationContext, "Added to favorites", Toast.LENGTH_SHORT).show()
                    } ?: kotlin.run {
                        Log.e("AnimeDetail", "Anime details not found")
                        // Handle error: Anime details not found
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Handle error: Failed to fetch anime details from API
                }
            }
        } else {
            Log.e("AnimeDetail", "malId not passed")
            // Handle error: malId not passed
        }
    }


}
