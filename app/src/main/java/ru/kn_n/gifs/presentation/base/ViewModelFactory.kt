package ru.kn_n.gifs.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kn_n.gifs.di.Scopes
import ru.kn_n.gifs.presentation.viewmodels.GifInfoViewModel
import ru.kn_n.gifs.presentation.viewmodels.MainViewModel
import ru.kn_n.gifs.presentation.viewmodels.SearchViewModel
import toothpick.Toothpick
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor() :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                Toothpick.openScope(Scopes.APP_SCOPE).getInstance(modelClass) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) ->
                Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.SEARCH_GIFS_SCOPE).getInstance(modelClass) as T
            modelClass.isAssignableFrom(GifInfoViewModel::class.java) ->
                Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.GIF_INFO_SCOPE).getInstance(modelClass) as T
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}