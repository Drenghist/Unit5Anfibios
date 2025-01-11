package com.example.unit5anfibios.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.unit5anfibios.AnfibiosFotosApplication
import com.example.unit5anfibios.data.AnfibiosRepository
import com.example.unit5anfibios.model.AnfibiosFotos
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AnfibiosUiState {
    data class Success(val fotos: List<AnfibiosFotos>) : AnfibiosUiState
    object Error : AnfibiosUiState
    object Loading : AnfibiosUiState
}

class AnfibiosViewModel (private val anfibiosRepository: AnfibiosRepository) : ViewModel() {

    var anfibiosUiState: AnfibiosUiState by mutableStateOf(AnfibiosUiState.Loading)
        private set

    init {
        getAnfibiosFotos()
    }

    fun getAnfibiosFotos(){
        viewModelScope.launch {
            anfibiosUiState = AnfibiosUiState.Loading
            anfibiosUiState = try {
                AnfibiosUiState.Success(anfibiosRepository.getAnfibiosFotos())

            } catch (e: IOException) {
                AnfibiosUiState.Error
            } catch (e: HttpException) {
                AnfibiosUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =(this[APPLICATION_KEY] as AnfibiosFotosApplication)
                val anfibiosRepository = application.container.anfibiosRepository
                AnfibiosViewModel (anfibiosRepository = anfibiosRepository)

            }

        }
    }
}

