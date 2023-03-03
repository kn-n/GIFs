package ru.kn_n.gifs.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import ru.kn_n.gifs.databinding.FragmentDetailsBinding
import ru.kn_n.gifs.domain.entities.GifInfoEntity
import ru.kn_n.gifs.presentation.base.BaseFragment
import ru.kn_n.gifs.presentation.viewmodels.GifInfoViewModel
import ru.kn_n.gifs.utils.gone
import ru.kn_n.gifs.utils.isShow
import ru.kn_n.gifs.utils.loadImage
import ru.kn_n.gifs.utils.show
import javax.inject.Inject

class GifInfoFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    @Inject
    lateinit var viewModel: GifInfoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        getGifInfo()

        setupButtons()
    }

    private fun setupButtons() {
        binding.back.setOnClickListener {
            viewModel.routBack()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[GifInfoViewModel::class.java]
    }

    private fun getGifInfo() {
        viewModel.getGifInfo()
        viewModel.resultGifInfo.observe(viewLifecycleOwner) {
            showRequestResult(
                resource = it,
                doOnSuccess = { data -> doOnSuccess(data) },
                doOnLoading = { doOnLoading() },
                doOnError = { message -> doOnError(message) }
            )
        }
    }

    private fun doOnSuccess(data: GifInfoEntity) {
        with(binding) {
            infoBlock.show()
            loading.gone()
            errorText.gone()
        }
        when (data) {
            is GifInfoEntity.GifFullInfoEntity -> showFullInfo(data)
            is GifInfoEntity.GifShortInfoEntity -> showShortInfo(data)
            is GifInfoEntity.GifEmptyEntity -> doOnError(EMPTY_ERROR)
        }
    }

    private fun doOnLoading() {
        with(binding) {
            loading.show()
            infoBlock.gone()
            errorText.gone()
        }
    }

    private fun doOnError(message: String) {
        with(binding) {
            errorText.show()
            errorText.text = message
            loading.gone()
            infoBlock.gone()
        }
    }

    private fun showFullInfo(data: GifInfoEntity.GifFullInfoEntity) {
        with(binding) {
            userBlock.show()
            instagram.isShow(data.instagramUrl.isNotEmpty())
            web.isShow(data.source.isNotEmpty())

            loadImage(
                view = userImg,
                url = data.avatarUrl,
                place = userImg,
                error = userError,
                loading = userLoading
            )

            displayUsername.text = data.displayUsername
            username.text = data.username

            instagram.setOnClickListener {
                followTheLink(data.instagramUrl)
            }

            web.setOnClickListener {
                followTheLink(data.source)
            }

            gifDescription.text = data.title

            loadImage(
                view = gif,
                url = data.url,
                place = gif,
                error = error,
                loading = gifLoading
            )
        }
    }

    private fun showShortInfo(data: GifInfoEntity.GifShortInfoEntity) {
        with(binding) {
            userBlock.gone()
            instagram.gone()
            web.isShow(data.source.isNotEmpty())

            web.setOnClickListener {
                followTheLink(data.source)
            }

            gifDescription.text = data.title

            loadImage(
                view = gif,
                url = data.url,
                place = gif,
                error = error,
                loading = gifLoading
            )
        }
    }

    companion object {
        const val EMPTY_ERROR = "Something goes wrong"
    }
}