package com.example.unit5anfibios.data

import com.example.unit5anfibios.model.AnfibiosFotos
import com.example.unit5anfibios.network.AnfibiosApiService

interface AnfibiosRepository {
    suspend fun getAnfibiosFotos(): List<AnfibiosFotos>
}

class NetworkAnfibiosRepository(private val anfibiosApiService: AnfibiosApiService) : AnfibiosRepository {
    override suspend fun getAnfibiosFotos(): List<AnfibiosFotos>  = anfibiosApiService.getAnfibios()
}