package ru.kn_n.gifs.di

import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import ru.kn_n.gifs.data.api.APIGifInfo
import ru.kn_n.gifs.data.api.APISearch
import ru.kn_n.gifs.data.api.RetrofitClientGifInfo
import ru.kn_n.gifs.data.api.RetrofitClientSearch
import ru.kn_n.gifs.presentation.models.CacheGifId
import toothpick.ktp.binding.module

fun appModule(context: Context) = module {
    //Global
    bind(Context::class.java).toInstance(context)

    //Navigation
    val cicerone = Cicerone.create()
    bind(Router::class.java).toInstance(cicerone.router)
    bind(NavigatorHolder::class.java).toInstance(cicerone.getNavigatorHolder())

    bind(APISearch::class.java).toProvider(RetrofitClientSearch::class.java)
    bind(APIGifInfo::class.java).toProvider(RetrofitClientGifInfo::class.java)

    bind(CacheGifId::class.java).singleton()
}