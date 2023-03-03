package ru.kn_n.gifs.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.kn_n.gifs.data.api.APISearch
import ru.kn_n.gifs.data.mappers.SearchResponseMapper
import ru.kn_n.gifs.domain.entities.DataEntity

class PagingDataSource(
    private val api: APISearch,
    private val mapper: SearchResponseMapper,
    private val query: String
) : PagingSource<Int, DataEntity>() {
    override fun getRefreshKey(state: PagingState<Int, DataEntity>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataEntity> {
        val pageNumber = params.key ?: ZERO_PAGE
        return try {
            val response = mapper.mapSearchResponse(
                api.getGifs(
                    q = query,
                    offset = pageNumber.toString()
                )
            )
            val pageResponse = response.pagination
            val data = response.data

            var nextPageNumber: Int? = null
            if (pageResponse.count > MIN_COUNT) {
                nextPageNumber = pageNumber + pageResponse.count
            }

            LoadResult.Page(
                data = data,
                prevKey = if (pageNumber == ZERO_PAGE) null else pageNumber,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val ZERO_PAGE = 0
        const val MIN_COUNT = 1
    }
}