package ru.shitter.data.net.common

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object Network {

    val appJson = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        prettyPrint = true
    }

    fun getHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    fun getRetrofit(url: String, json: Json, client: OkHttpClient) = Retrofit.Builder()
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(url)
        .build()

    inline fun <reified T> getApi(retrofit: Retrofit) = retrofit.create(T::class.java)
}