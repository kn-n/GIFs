package ru.kn_n.gifs.data.mappers

import ru.kn_n.gifs.data.models.GifInfoResponse
import ru.kn_n.gifs.domain.entities.GifInfoEntity
import toothpick.InjectConstructor

@InjectConstructor
class GifInfoResponseMapper {
    fun mapGifInfoResponse(response: GifInfoResponse): GifInfoEntity {
        return response.data?.let { data ->
            data.user?.let {
                GifInfoEntity.GifFullInfoEntity(
                    id = data.id.orEmpty(),
                    url = data.images?.let { images ->
                        images.original?.let { image ->
                            image.url.orEmpty()
                        }.orEmpty()
                    }.orEmpty(),
                    source = data.url.orEmpty(),
                    title = data.title.orEmpty(),
                    avatarUrl = data.user.avatar_url.orEmpty(),
                    username = data.user.username?.let { "@${it}" }.orEmpty(),
                    displayUsername = data.user.display_name.orEmpty(),
                    instagramUrl = data.user.instagram_url.orEmpty()
                )
            } ?: GifInfoEntity.GifShortInfoEntity(
                id = data.id.orEmpty(),
                url = data.images?.let { images ->
                    images.original?.let { image ->
                        image.url.orEmpty()
                    }.orEmpty()
                }.orEmpty(),
                source = data.url.orEmpty(),
                title = data.title.orEmpty()
            )
        } ?: GifInfoEntity.GifEmptyEntity
    }
}