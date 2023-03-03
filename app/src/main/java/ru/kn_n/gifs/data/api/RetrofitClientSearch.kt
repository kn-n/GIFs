package ru.kn_n.gifs.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kn_n.gifs.utils.Constants.BASE_URL
import javax.inject.Inject
import javax.inject.Provider

class RetrofitClientSearch @Inject constructor() : Provider<APISearch> {
    override fun get(): APISearch {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APISearch::class.java)
    }
}