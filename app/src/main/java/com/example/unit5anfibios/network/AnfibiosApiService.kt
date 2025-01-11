package com.example.unit5anfibios.network

import com.example.unit5anfibios.model.AnfibiosFotos
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("Application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface AnfibiosApiService{
    @GET("amphibians")
    suspend fun getAnfibios(): List<AnfibiosFotos>
}