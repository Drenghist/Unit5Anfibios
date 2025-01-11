package com.example.unit5anfibios.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnfibiosFotos(
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value="img_src")
    val imgSrc: String
)