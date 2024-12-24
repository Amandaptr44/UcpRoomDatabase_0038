package com.example.ucp2pam.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Dokter
import com.example.ucp2pam.data.entity.Jadwal
import com.example.ucp2pam.repository.RepositoryDokter
import com.example.ucp2pam.repository.RepositoryJadwal
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeJadwalViewModel(
    private val repositoryJadwal: RepositoryJadwal
) : ViewModel() {
    val homeUiState: StateFlow<HomeUiStateJadwal> = repositoryJadwal.getAllJadwal()
        .filterNotNull()
        .map {
            HomeUiStateJadwal(
                listJadwal = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit (HomeUiStateJadwal(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiStateJadwal(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )

        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiStateJadwal(
                isLoading = true,
            )
        )
}

data class HomeUiStateJadwal(
    val listJadwal: List<Jadwal> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

