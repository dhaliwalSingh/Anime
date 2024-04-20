package com.example.anime.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnimeDao {
    // Other methods...

    @Insert
    fun insertAnime(anime : Anime)

    @Delete
    fun deleteAnime(anime : Anime)

    @Query("DELETE FROM table_fav_anime")
    fun deleteAllAnime()

    @Query("SELECT * FROM table_fav_anime")
    fun getAllAnime(): LiveData<List<Anime>>

    @Query("SELECT * FROM table_fav_anime WHERE MalId = :Malid")
    fun getAnimeById(Malid: Int): LiveData<Anime> // Ensure MalId is not nullable and adjust the query accordingly


}

