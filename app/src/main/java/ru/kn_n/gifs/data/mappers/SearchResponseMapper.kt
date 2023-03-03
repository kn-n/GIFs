package ru.kn_n.gifs.data.mappers

import ru.kn_n.gifs.data.models.DataResponse
import ru.kn_n.gifs.data.models.PaginationResponse
import ru.kn_n.gifs.data.models.SearchResponse
import ru.kn_n.gifs.domain.entities.DataEntity
import ru.kn_n.gifs.domain.entities.PaginationEntity
import ru.kn_n.gifs.domain.entities.SearchEntity
import ru.kn_n.gifs.utils.NULL
import toothpick.InjectConstructor

@InjectConstructor
class SearchResponseMapper {
    fun mapSearchResponse(response: SearchResponse): SearchEntity {
        return SearchEntity(
            data = response.data?.let { list -> list.map { mapDataResponse(it) } } ?: emptyList(),
            pagination = response.pagination?.let { mapPaginationResponse(it) } ?: PaginationEntity()
        )
    }

    private fun mapDataResponse(data: DataResponse): DataEntity {
        return DataEntity(
            id = data.id.orEmpty(),
            url = data.images?.let { images ->
                images.original?.let { image ->
                    image.url.orEmpty()
                }.orEmpty()
            }.orEmpty()
        )
    }

    private fun mapPaginationResponse(pagination: PaginationResponse): PaginationEntity {
        return PaginationEntity(
            totalCount = pagination.total_count ?: Int.NULL,
            count = pagination.count ?: Int.NULL,
            offset = pagination.offset ?: Int.NULL
        )
    }
}