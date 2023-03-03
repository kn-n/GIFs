package ru.kn_n.gifs.domain.repositories

import ru.kn_n.gifs.domain.entities.GifInfoEntity

interface GifInfoRepository {
    suspend fun getGifInfo(id: String): GifInfoEntity
}