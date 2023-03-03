package ru.kn_n.gifs.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.kn_n.gifs.data.models.SearchResponse
import ru.kn_n.gifs.utils.Constants.API_KEY
import ru.kn_n.gifs.utils.Constants.LIMIT

interface APISearch {
    @GET("search")
    suspend fun getGifs(
        @Query("api_key") api_key: String = API_KEY,
        @Query("q") q: String,
        @Query("limit") limit: String = LIMIT,
        @Query("offset") offset: String,
    ): SearchResponse
}