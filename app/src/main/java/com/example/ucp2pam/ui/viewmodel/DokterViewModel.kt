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

    // Validasi data input pengguna
    fun validateFieldsDokter(): Boolean {
        val event = uiState.dokterEvent
        val errorStateDokter = FormErrorStateDokter(
            id = if (event.id.isNotEmpty()) null else "ID tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            spesialis = if (event.spesialis.isNotEmpty()) null else "Spesialis tidak boleh kosong",
            klinik = if (event.klinik.isNotEmpty()) null else "Klinik tidak boleh kosong",
            noHp = if (event.noHp.isNotEmpty()) null else "No HP tidak boleh kosong",
            jamKerja = if (event.jamKerja.isNotEmpty()) null else "Jam Kerja tidak boleh kosong",
        )

        uiState = uiState.copy(isEntryValid = errorStateDokter)
        return errorStateDokter.isValid()
    }

    // Menyimpan data ke repository
    fun saveDataDokter() {
        val currentEvent = uiState.dokterEvent

        if (validateFieldsDokter()) {
            viewModelScope.launch {
                try {
                    repositoryDokter.insertDokter(currentEvent.toDokterEntity())
                    uiState = uiState.copy(
                        snackbarMessage = "Data berhasil disimpan",
                        dokterEvent = DokterEvent(), // Reset input form
                        isEntryValid = FormErrorStateDokter(), // Reset error state
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackbarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }

