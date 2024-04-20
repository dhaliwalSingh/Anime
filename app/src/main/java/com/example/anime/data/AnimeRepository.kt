package com.example.anime.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData

class AnimeRepository(application: Context) {
    private var db: AppDB?
    private val animeDao = application.let { AppDB.getInstance(application)?.animeDao() }

    init {
        db = AppDB.getInstance(application)
    }

    fun insertAnime(newAnimeToInsert: Anime) { // Change the parameter type to Anime
        AppDB.databaseWriteExecutor.execute {
            animeDao?.insertAnime(newAnimeToInsert)
        }
    }

    fun deleteAllAnime() {
        AppDB.databaseWriteExecutor.execute {
            animeDao?.deleteAllAnime()
        }
    }

    suspend fun deleteAnime(anime: Anime) {
        animeDao?.deleteAnime(anime)
    }

    fun getAllAnime(): LiveData<List<Anime>>? {
        // Fetch all anime data from the database
        return animeDao?.getAllAnime()
    }

    fun getAnimeById(malId: Int): LiveData<Anime>? {
        Log.d("AnimeRepository", "Getting Anime by Id: $malId")
        return animeDao?.getAnimeById(malId)
    }
}
