package com.example.anime.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import java.util.concurrent.Executors

@Database(entities = [Anime::class], version = 2, exportSchema = false)
@TypeConverters(ImagesTypeConverter::class)
abstract class AppDB : RoomDatabase() {
    abstract fun animeDao() : AnimeDao?

    companion object{
        @Volatile
        private var db : AppDB? = null

        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getInstance(context : Context) :AppDB?{
            if(db == null){
                db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "db_anime"
                )   .fallbackToDestructiveMigration()
                    .build()
            }
            return db
        }
    }
}
