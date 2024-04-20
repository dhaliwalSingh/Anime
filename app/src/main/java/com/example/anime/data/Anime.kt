package com.example.anime.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class APIResponse<T>(val data: T) {
    override fun toString(): String {
        return "APIResponse(data=$data)"
    }
}

@Entity(tableName = "table_fav_anime")
data class Anime(
    @Json(name = "title") val title: String?,
    @Json(name = "mal_id") val malId: Int?,
    @Json(name = "episodes") val episodes: Int?,
    @Json(name = "year") val year: Int?, // Make it nullable
    @Json(name = "type") val type: String?,
    @Json(name = "synopsis") val synopsis: String?,
    @Json(name = "images") val images: Images
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    override fun toString(): String {
        return "Anime : $title, $malId, $episodes, $year, $type, $synopsis, $imageURL"
    }

    val imageURL: String
        get() = images.jpg.imageURL
}

data class Images(
    @Json(name = "jpg") val jpg: Image
)

data class Image(
    @Json(name = "image_url") val imageURL: String
)
