package ru.kn_n.gifs.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.kn_n.gifs.databinding.FragmentSearchBinding
import ru.kn_n.gifs.presentation.adapters.SearchAdapter
import ru.kn_n.gifs.presentation.base.BaseFragment
import ru.kn_n.gifs.presentation.viewmodels.SearchViewModel
import ru.kn_n.gifs.utils.gone
import ru.kn_n.gifs.utils.show
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    @Inject
    lateinit var viewModel: SearchViewModel

    private val searchAdapter by lazy {
        SearchAdapter { id -> onItemClick(id) }
    }

    private var queryTextChangedJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupAdapter()
        setupSearch()
    }

    private fun setupSearch() {
        binding.search.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrEmpty()) {
                        getGifs(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    queryTextChangedJob?.cancel()
                    if (!newText.isNullOrEmpty() && binding.search.hasFocus()) {
                        queryTextChangedJob = lifecycleScope.launch(Dispatchers.Main) {
                            delay(DELAY_500)
                            getGifs(newText)
                        }
                    }
                    return false
                }
            }
        )
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
    }

    private fun setupAdapter() {
        binding.recyclerGifs.apply {
            layoutManager =
                GridLayoutManager(requireContext(), SPAN_COUNT, GridLayoutManager.VERTICAL, false)
            adapter = searchAdapter
            itemAnimator = null
        }
    }

    private fun getGifs(q: String) {
        searchAdapter.submitData(lifecycle, PagingData.empty())
        viewModel.getGifs(q)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.resultSearch.collectLatest { pagingData ->
                launch(Dispatchers.Main) {
                    searchAdapter.loadStateFlow.collectLatest {
                        when (it.refresh) {
                            is LoadState.Loading -> doOnLoading()
                            is LoadState.Error -> doOnError("Error")
                            else -> if (searchAdapter.itemCount < 1) {
                                doOnError("Oh, it's empty")
                            } else {
                                doOnSuccess()
                            }
                        }

                    }
                }
                searchAdapter.submitData(pagingData)
            }
        }
    }

    private fun doOnSuccess() {
        with(binding) {
            recyclerGifs.show()
            loading.gone()
            errorText.gone()
        }
    }

    private fun doOnLoading() {
        with(binding) {
            loading.show()
            errorText.gone()
        }
    }

    private fun doOnError(error: String) {
        with(binding) {
            errorText.show()
            errorText.text = error
            loading.gone()
        }
    }

    private fun onItemClick(id: String) {
        viewModel.routToGifInfoScreen(id)
    }

    companion object {
        const val DELAY_500: Long = 500
        const val SPAN_COUNT = 2
    }
}