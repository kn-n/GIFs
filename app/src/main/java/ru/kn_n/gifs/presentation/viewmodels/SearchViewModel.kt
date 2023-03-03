package ru.kn_n.gifs.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import ru.kn_n.gifs.domain.entities.DataEntity
import ru.kn_n.gifs.domain.interactors.SearchInteractor
import ru.kn_n.gifs.presentation.base.BaseViewModel
import ru.kn_n.gifs.presentation.models.CacheGifId
import ru.kn_n.gifs.presentation.navigation.Screens
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val router: Router,
    private val searchInteractor: SearchInteractor,
    private val cache: CacheGifId
) : BaseViewModel() {

    private lateinit var _resultSearch: Flow<PagingData<DataEntity>>
    val resultSearch: Flow<PagingData<DataEntity>>
        get() = _resultSearch

    fun getGifs(q: String) = launchPagingAsync(
        execute = { searchInteractor.getGifs(q).cachedIn(viewModelScope) },
        onSuccess = { _resultSearch = it }
    )

    fun routToGifInfoScreen(id: String) {
        cache.gifId = id
        router.navigateTo(Screens.gifInfoScreen())
    }
}