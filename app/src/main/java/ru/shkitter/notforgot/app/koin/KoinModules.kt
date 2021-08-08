package ru.shkitter.notforgot.app.koin

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.shkitter.data.net.common.Network
import ru.shkitter.notforgot.BuildConfig

object KoinModules {

    private val dataBaseModule = module { }

    private val networkModule = module {
        single { Network.appJson }
        single { Network.getHttpClient() }
        single {
            Network.getRetrofit(
                url = BuildConfig.API_URL,
                json = get(),
                client = get()
            )
        }
    }

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