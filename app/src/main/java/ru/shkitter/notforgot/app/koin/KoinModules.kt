package ru.shkitter.notforgot.app.koin

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object KoinModules {

    private val dataBaseModule = module { }

    private val networkModule = module { }

    private val dataSourceModule = module { }

    private val useCaseModule = module { }

    private val baseModule = module {
        loadKoinModules(
            listOf(
                dataBaseModule,
                networkModule,
                dataSourceModule,
                useCaseModule
            )
        )
    }

    val all = listOf(baseModule, presentationModule)
}