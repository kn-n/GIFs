package ru.kn_n.gifs.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ru.kn_n.gifs.data.api.APISearch
import ru.kn_n.gifs.data.mappers.SearchResponseMapper
import ru.kn_n.gifs.data.paging.PagingDataSource
import ru.kn_n.gifs.domain.repositories.SearchRepository
import ru.kn_n.gifs.utils.Constants.LIMIT
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: APISearch,
    private val mapper: SearchResponseMapper
) : SearchRepository {
    override fun getGifs(q: String) =
        Pager(
            config = PagingConfig(
                pageSize = LIMIT.toInt(),
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingDataSource(
                    api = api,
                    mapper = mapper,
                    query = q
                )
            }
        ).flow
}