package ru.shkitter.notforgot.app.koin

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.shkitter.data.db.common.AppDatabase
import ru.shkitter.data.db.common.Database
import ru.shkitter.data.db.session.SessionDataSourceImpl
import ru.shkitter.data.net.auth.AuthApi
import ru.shkitter.data.net.auth.AuthDataSourceImpl
import ru.shkitter.data.net.common.Network
import ru.shkitter.domain.auth.*
import ru.shkitter.domain.session.SessionDataSource
import ru.shkitter.domain.validation.ValidationUseCase
import ru.shkitter.domain.validation.ValidationUseCaseImpl
import ru.shkitter.notforgot.BuildConfig

object KoinModules {

    private val dataBaseModule = module {
        single { Database.build(androidContext()) }
        single { get<AppDatabase>().getSessionDao() }
    }

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
        factory<SessionDataSource> { SessionDataSourceImpl(sessionDao = get()) }
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