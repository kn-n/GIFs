package ru.kn_n.gifs.data.models

data class SearchResponse(
    val data: List<DataResponse>?,
    val pagination: PaginationResponse?
)

data class DataResponse(
    val id: String?,
    val images: ImagesResponse?
)

data class PaginationResponse(
    val total_count: Int?,
    val count: Int?,
    val offset: Int?
)

data class ImagesResponse(
    val original: UrlResponse?
)

data class UrlResponse(
    val url: String?
)