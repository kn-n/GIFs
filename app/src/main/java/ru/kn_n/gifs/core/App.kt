package ru.kn_n.gifs.core

import android.app.Application
import androidx.annotation.VisibleForTesting
import ru.kn_n.gifs.BuildConfig
import ru.kn_n.gifs.di.Scopes
import ru.kn_n.gifs.di.appModule
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initToothpick()
        initAppScope(Toothpick.openScope(Scopes.APP_SCOPE))
    }

    @VisibleForTesting
    fun initAppScope(appScope: Scope) {
        appScope.installModules(
            appModule(this)
        )
        Toothpick.inject(this, appScope)
    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().preventMultipleRootScopes())
        }
    }
}