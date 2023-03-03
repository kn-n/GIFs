package ru.kn_n.gifs.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kn_n.gifs.utils.Constants.BASE_URL
import javax.inject.Inject
import javax.inject.Provider

class RetrofitClientGifInfo @Inject constructor() : Provider<APIGifInfo> {
    override fun get(): APIGifInfo {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIGifInfo::class.java)
    }
}