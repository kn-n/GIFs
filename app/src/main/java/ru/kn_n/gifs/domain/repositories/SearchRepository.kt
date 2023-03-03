package ru.kn_n.gifs.domain.repositories

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.kn_n.gifs.domain.entities.DataEntity

interface SearchRepository {
    fun getGifs(q: String): Flow<PagingData<DataEntity>>
}