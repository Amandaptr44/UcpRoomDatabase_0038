package com.example.ucp2pam.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Jadwal
import com.example.ucp2pam.repository.RepositoryJadwal
import com.example.ucp2pam.ui.navigation.DestinasiDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailJadwalViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryJadwal: RepositoryJadwal,

    ) : ViewModel() {
    private val _jadwalId: String = checkNotNull(savedStateHandle[DestinasiDetail.jadwalId])

    val detailUIState: StateFlow<DetailUiState> = repositoryJadwal.getJadwal(_jadwalId)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Keslahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true,
            ),
        )
    fun deleteJadwal () {
        detailUIState.value.detailUiEvent.toJadwalEntity().let{
            viewModelScope.launch {
                repositoryJadwal.deleteJadwal(it)
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: JadwalEvent = JadwalEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == JadwalEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != JadwalEvent()
}


/*
*Data class untuk menampung data yang akan ditampilkan di UI
 */

//memindahkan data dari entity ke ui
fun Jadwal.toDetailUiEvent(): JadwalEvent{
    return JadwalEvent(
        jadwalId = jadwalId,
        namaPasien = namaPasien,
        noPasien = noPasien,
        namaDokter = namaDokter,
        tanggal = tanggal,
        status = status,
    )
}

