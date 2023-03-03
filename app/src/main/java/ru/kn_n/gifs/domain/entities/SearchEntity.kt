package ru.kn_n.gifs.domain.entities

import ru.kn_n.gifs.utils.NULL

data class SearchEntity(
    val data: List<DataEntity>,
    val pagination: PaginationEntity
)

data class DataEntity(
    val id: String,
    val url: String
)

data class PaginationEntity(
    val totalCount: Int = Int.NULL,
    val count: Int = Int.NULL,
    val offset: Int = Int.NULL
)
