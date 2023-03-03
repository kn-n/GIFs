package ru.kn_n.gifs.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import ru.kn_n.gifs.domain.entities.GifInfoEntity
import ru.kn_n.gifs.domain.interactors.GifInfoInteractor
import ru.kn_n.gifs.presentation.base.BaseViewModel
import ru.kn_n.gifs.presentation.models.CacheGifId
import ru.kn_n.gifs.presentation.navigation.Screens
import ru.kn_n.gifs.utils.Resource
import javax.inject.Inject

class GifInfoViewModel @Inject constructor(
    private val router: Router,
    private val gifInfoInteractor: GifInfoInteractor,
    private val cache: CacheGifId
) : BaseViewModel() {

    private val _resultGifInfo = MutableLiveData<Resource<GifInfoEntity>>()
    val resultGifInfo: LiveData<Resource<GifInfoEntity>> = _resultGifInfo

    fun getGifInfo() {
        requestWithLiveData(_resultGifInfo) {
            gifInfoInteractor.getGifInfo(cache.gifId)
        }
    }

    fun routBack() {
        router.backTo(Screens.searchScreen())
    }
}