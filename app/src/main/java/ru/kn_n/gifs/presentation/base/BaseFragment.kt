package ru.kn_n.gifs.presentation.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.kn_n.gifs.di.Scopes
import ru.kn_n.gifs.utils.Resource
import ru.kn_n.gifs.utils.Status
import toothpick.Toothpick
import javax.inject.Inject

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)

        setupViewModelFactory()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun <T> showRequestResult(
        resource: Resource<T>,
        doOnSuccess: (data: T) -> Unit,
        doOnError: (message: String) -> Unit,
        doOnLoading: () -> Unit
    ) {
        when (resource.status) {
            Status.LOADING -> doOnLoading()
            Status.SUCCESS -> resource.data?.let(doOnSuccess) ?: doOnError(ERROR_NO_RESULT)
            Status.ERROR -> doOnError(ERROR)
        }
    }

    private fun setupViewModelFactory() {
        viewModelFactory = Toothpick.openScope(Scopes.APP_SCOPE).getInstance(ViewModelFactory::class.java)
    }

    fun followTheLink(url: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        startActivity(intent)
    }

    companion object {
        const val ERROR_NO_RESULT = "No result :("
        const val ERROR = "Error :("
    }
}