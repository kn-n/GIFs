package ru.kn_n.gifs.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kn_n.gifs.data.models.GifInfoResponse
import ru.kn_n.gifs.utils.Constants

interface APIGifInfo {
    @GET("{id}")
    suspend fun getGifInfo(
        @Path("id") id: String,
        @Query("api_key") api_key: String = Constants.API_KEY
    ): GifInfoResponse
}