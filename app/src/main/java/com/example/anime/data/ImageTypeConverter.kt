package com.example.anime.data

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ImagesTypeConverter {
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val adapter = moshi.adapter(Images::class.java)

    @TypeConverter
    fun fromImages(images: Images): String {
        return adapter.toJson(images)
    }

    @TypeConverter
    fun toImages(imagesJson: String): Images {
        return adapter.fromJson(imagesJson) ?: Images(Image(""))
    }
}
