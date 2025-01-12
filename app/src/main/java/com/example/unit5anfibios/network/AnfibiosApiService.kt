package com.example.unit5anfibios.network

import com.example.unit5anfibios.model.AnfibiosFotos

import retrofit2.http.GET


interface AnfibiosApiService{
    @GET("amphibians")
    suspend fun getAnfibios(): List<AnfibiosFotos>
}