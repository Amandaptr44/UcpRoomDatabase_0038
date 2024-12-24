package com.example.ucp2pam.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.ui.customwidget.TopAppBarp
import com.example.ucp2pam.ui.navigation.AlamatNavigasi
import com.example.ucp2pam.ui.viewmodel.DokterEvent
import com.example.ucp2pam.ui.viewmodel.DokterUiState
import com.example.ucp2pam.ui.viewmodel.DokterViewModel
import com.example.ucp2pam.ui.viewmodel.FormErrorStateDokter
import com.example.ucp2pam.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiInsertDokter : AlamatNavigasi {
    override val route = "insert_dok"
}

@Composable
fun InsertDokterView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DokterViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBarp(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Dokter"
            )
        },
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .padding(padding)
        ) {
            InsertBodyDokter(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveDataDokter()
                    }
                    onNavigate()
                }
            )
        }
    }
}


@Composable
fun InsertBodyDokter(
    modifier: Modifier = Modifier,
    onValueChange: (DokterEvent) -> Unit,
    uiState: DokterUiState,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FormDokter(
                dokterEvent = uiState.dokterEvent,
                onValueChange = onValueChange,
                errorState = uiState.isEntryValid
            )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDokter(
    dokterEvent: DokterEvent = DokterEvent(),
    onValueChange: (DokterEvent) -> Unit,
    errorState: FormErrorStateDokter = FormErrorStateDokter(),
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.fillMaxWidth()
    ){

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.nama,
            onValueChange = {
                onValueChange(dokterEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan nama") },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color(0xFF42A5F5),
                focusedBorderColor = Color(0xFF42A5F5),
            )
        )
        Text(text = errorState.nama ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.dokter_id,
            onValueChange = {
                onValueChange(dokterEvent.copy(dokter_id = it))
            },
            label = { Text("Id Dokter") },
            isError = errorState.dokter_id != null,
            placeholder = { Text("Masukkan Id Dokter") },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color(0xFF42A5F5),
                focusedBorderColor = Color(0xFF42A5F5),
            )
        )
        Text(text = errorState.dokter_id ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.spesialis,
            onValueChange = {
                onValueChange(dokterEvent.copy(spesialis = it))
            },
            label = { Text("Spesialis") },
            isError = errorState.spesialis != null,
            placeholder = { Text("Masukkan spesialis") },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color(0xFF42A5F5),
                focusedBorderColor = Color(0xFF42A5F5),
            )
        )
        Text(text = errorState.spesialis ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.klinik,
            onValueChange = {
                onValueChange(dokterEvent.copy(klinik = it))
            },
            label = { Text("Klinik") },
            isError = errorState.klinik != null,
            placeholder = { Text("Masukkan Klinik") },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color(0xFF42A5F5),
                focusedBorderColor = Color(0xFF42A5F5),
            )
        )
        Text(text = errorState.klinik ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.noHp,
            onValueChange = {
                onValueChange(dokterEvent.copy(noHp = it))
            },
            label = { Text("NoHp") },
            isError = errorState.noHp != null,
            placeholder = { Text("Masukkan NoHp") },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color(0xFF42A5F5),
                focusedBorderColor = Color(0xFF42A5F5),
            )
        )
        Text(text = errorState.noHp ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.jamKerja,
            onValueChange = {
                onValueChange(dokterEvent.copy(jamKerja = it))
            },
            label = { Text("Jam Kerja") },
            isError = errorState.jamKerja != null,
            placeholder = { Text("Masukkan Jam Kerja") },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color(0xFF42A5F5),
                focusedBorderColor = Color(0xFF42A5F5),
            )
        )
        Text(text = errorState.jamKerja ?: "", color = Color.Red)

    }
}



