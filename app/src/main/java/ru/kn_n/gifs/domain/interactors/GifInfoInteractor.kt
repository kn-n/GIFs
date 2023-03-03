package ru.kn_n.gifs.domain.interactors

import ru.kn_n.gifs.data.repositories.GifInfoRepositoryImpl
import javax.inject.Inject

class GifInfoInteractor @Inject constructor(
    private val gifInfoRepository: GifInfoRepositoryImpl
) {
    suspend fun getGifInfo(id: String) = gifInfoRepository.getGifInfo(id)
}