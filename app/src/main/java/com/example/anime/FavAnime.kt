package com.example.anime

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anime.adapter.FavAnimeAdapter
import com.example.anime.data.AnimeRepository
import com.example.anime.databinding.ActivityFavAnimeBinding
import com.google.android.material.snackbar.Snackbar

class FavAnime : AppCompatActivity() {

    private lateinit var binding: ActivityFavAnimeBinding
    private lateinit var animeRepository: AnimeRepository
    private lateinit var favAnimeAdapter: FavAnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize repository and ViewModel
        animeRepository = AnimeRepository(application)

        // Initialize RecyclerView and Adapter
        favAnimeAdapter = FavAnimeAdapter(this, animeRepository)
        binding.tvFav.layoutManager = LinearLayoutManager(this)
        binding.tvFav.adapter = favAnimeAdapter

        // Load favorite anime from repository
        loadAllAnime()
    }

    private fun loadAllAnime() {
        // Fetch all anime list from repository
        animeRepository.getAllAnime()?.observe(this) { allAnimeList ->
            allAnimeList?.let {
                // Update the adapter with the fetched list of all anime
                favAnimeAdapter.setAnimeList(it)
            }
        }
    }

    // Callback for swipe-to-dismiss functionality
    private val swipeCallback = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            favAnimeAdapter.deleteItem(position)
            Snackbar.make(binding.root, "Anime deleted from favorites", Snackbar.LENGTH_LONG)
                .setAction("Undo") {
                    // Implement undo action if needed
                }.show()
        }
    }

}
