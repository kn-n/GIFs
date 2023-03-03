package ru.kn_n.gifs.domain.entities

sealed class GifInfoEntity {
    data class GifFullInfoEntity(
        val id: String,
        val url: String,
        val source: String,
        val title: String,
        val avatarUrl: String,
        val username: String,
        val displayUsername: String,
        val instagramUrl: String
    ) : GifInfoEntity()

    data class GifShortInfoEntity(
        val id: String,
        val url: String,
        val source: String,
        val title: String
    ) : GifInfoEntity()

    object GifEmptyEntity : GifInfoEntity()
}


