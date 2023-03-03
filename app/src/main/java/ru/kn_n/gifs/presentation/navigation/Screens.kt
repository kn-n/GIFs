package ru.kn_n.gifs.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kn_n.gifs.presentation.fragments.GifInfoFragment
import ru.kn_n.gifs.presentation.fragments.SearchFragment

object Screens {
    fun searchScreen() = FragmentScreen { SearchFragment() }

    fun gifInfoScreen() = FragmentScreen { GifInfoFragment() }
}