package ru.shkitter.notforgot.app.koin

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.shkitter.data.net.auth.AuthApi
import ru.shkitter.data.net.auth.AuthDataSourceImpl
import ru.shkitter.data.net.common.Network
import ru.shkitter.domain.auth.*
import ru.shkitter.domain.validation.ValidationUseCase
import ru.shkitter.domain.validation.ValidationUseCaseImpl
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

    private val apiModule = module {
        single { Network.getApi<AuthApi>(retrofit = get()) }
    }

    private val dataSourceModule = module {
        factory<AuthDataSource> { AuthDataSourceImpl(authApi = get()) }
    }

    private val useCaseModule = module {
        factory<LoginUseCase> { LoginUseCaseImpl(authDataSource = get()) }
        factory<RegistrationUseCase> { RegistrationUseCaseImpl(authDataSource = get()) }
        factory<ValidationUseCase> { ValidationUseCaseImpl() }
    }

    private val baseModule = module {
        loadKoinModules(
            listOf(
                dataBaseModule,
                networkModule,
                apiModule,
                dataSourceModule,
                useCaseModule
            )
        )
    }

    val all = listOf(baseModule, presentationModule)
}