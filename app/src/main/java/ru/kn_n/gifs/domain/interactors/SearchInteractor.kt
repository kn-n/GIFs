package ru.kn_n.gifs.domain.interactors

import ru.kn_n.gifs.data.repositories.SearchRepositoryImpl
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val searchRepository: SearchRepositoryImpl
) {
    fun getGifs(q: String) = searchRepository.getGifs(q)
}