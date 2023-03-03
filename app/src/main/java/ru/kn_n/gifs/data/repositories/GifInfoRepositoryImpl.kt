package ru.kn_n.gifs.data.repositories

import ru.kn_n.gifs.data.api.APIGifInfo
import ru.kn_n.gifs.data.mappers.GifInfoResponseMapper
import ru.kn_n.gifs.domain.repositories.GifInfoRepository
import javax.inject.Inject

class GifInfoRepositoryImpl @Inject constructor(
    private val api: APIGifInfo,
    private val mapper: GifInfoResponseMapper
) : GifInfoRepository {
    override suspend fun getGifInfo(id: String) = mapper.mapGifInfoResponse(api.getGifInfo(id))
}