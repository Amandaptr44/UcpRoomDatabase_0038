package com.example.ucp2pam.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Dokter
import com.example.ucp2pam.repository.RepositoryDokter
import kotlinx.coroutines.launch

class DokterViewModel(
    private val repositoryDokter: RepositoryDokter
) : ViewModel() {

    var uiState by mutableStateOf(DokterUiState())

    // Memperbarui state berdasarkan input pengguna
    fun updateState(dokterEvent: DokterEvent) {
        uiState = uiState.copy(
            dokterEvent = dokterEvent,
        )
    }

