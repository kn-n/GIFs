package ru.kn_n.gifs.data.models

data class GifInfoResponse(
    val data: GifDataResponse?
)

data class GifDataResponse(
    val id: String?,
    val url: String?,
    val username: String?,
    val title: String?,
    val images: GifImagesResponse?,
    val user: UserResponse?
)

data class UserResponse(
    val avatar_url: String?,
    val username: String?,
    val display_name: String?,
    val instagram_url: String?
)

data class GifImagesResponse(
    val original: GifUrlResponse?
)

data class GifUrlResponse(
    val url: String?
)

