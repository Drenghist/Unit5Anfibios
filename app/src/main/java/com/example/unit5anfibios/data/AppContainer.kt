package com.example.unit5anfibios.data

import com.example.unit5anfibios.network.AnfibiosApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val anfibiosRepository : AnfibiosRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit : Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("Application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AnfibiosApiService by lazy {
        retrofit.create(AnfibiosApiService::class.java)
    }

    override val anfibiosRepository : AnfibiosRepository by lazy{
        NetworkAnfibiosRepository(retrofitService)
    }
}