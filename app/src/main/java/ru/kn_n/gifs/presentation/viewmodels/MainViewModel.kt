package ru.kn_n.gifs.presentation.viewmodels

import com.github.terrakok.cicerone.Router
import ru.kn_n.gifs.presentation.base.BaseViewModel
import ru.kn_n.gifs.presentation.navigation.Screens
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: Router
) : BaseViewModel() {

    fun routToSearchScreen() {
        router.newRootScreen(Screens.searchScreen())
    }
}