package com.example.ucp2pam.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.data.entity.Dokter
import com.example.ucp2pam.ui.customwidget.TopAppBarp
import com.example.ucp2pam.ui.navigation.AlamatNavigasi
import com.example.ucp2pam.ui.viewmodel.DokterViewModel
import com.example.ucp2pam.ui.viewmodel.FormErrorState
import com.example.ucp2pam.ui.viewmodel.JadwalEvent
import com.example.ucp2pam.ui.viewmodel.JadwalUIState
import com.example.ucp2pam.ui.viewmodel.JadwalViewModel
import com.example.ucp2pam.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiInsertJadwal: AlamatNavigasi {
    override val route = "insert_jdw"
}

@Composable
fun InsertJadwalView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: JadwalViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiStateJadwal = viewModel.uiStateJadwal
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiStateJadwal.snackBarMessage) {
        uiStateJadwal.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .padding(padding)
        ) {
            TopAppBarp(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Jadwal"
                )
            InsertBodyJadwal(
                uiState = uiStateJadwal,
                dokterList = uiStateJadwal.dokterList,
                onValueChange = { updateEvent -> viewModel.updateStateJadwal(updateEvent) },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveDataJadwal()
                        if (viewModel.validateFields()) {
                            viewModel.saveDataJadwal()
                            delay(500)
                            withContext(Dispatchers.Main) {
                                onNavigate()
                            }

                        }
                    }
                },
            )
        }
    }
}

@Composable
fun InsertBodyJadwal(
    modifier: Modifier = Modifier,
    onValueChange: (JadwalEvent) -> Unit,
    uiState: JadwalUIState,
    dokterList: List<Dokter>,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormJdw(
            jadwalEvent = uiState.jadwalEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth(),
            dokterList = dokterList
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
fun FormJdw(
    jadwalEvent: JadwalEvent = JadwalEvent(),
    onValueChange: (JadwalEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier,
    dokterList: List<Dokter>,
) {
    val status = listOf("Pasien Baru", "Kontrol")

    var chosenDropdown by remember { mutableStateOf(jadwalEvent.namaPasien) }
    var expanded by remember { mutableStateOf(false) }

    Column (
        modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.jadwalId,
            onValueChange = {
                onValueChange(jadwalEvent.copy(jadwalId = it))
            },
            label = { Text("Id jadwal") },
            isError = errorState.jadwalId != null,
            placeholder = { Text("Masukkan Id jadwal") },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color(0xFF42A5F5),
                focusedBorderColor = Color(0xFF42A5F5),
            )
        )
        Text(text = errorState.jadwalId ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.namaPasien,
            onValueChange = {
                onValueChange(jadwalEvent.copy(namaPasien = it))
            },
            label = { Text("Nama") },
            isError = errorState.namaPasien != null,
            placeholder = { Text("Masukkan nama pasien") },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color(0xFF42A5F5),
                focusedBorderColor = Color(0xFF42A5F5),
            )
        )
        Text(text = errorState.namaPasien ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.noPasien,
            onValueChange = {
                onValueChange(jadwalEvent.copy(noPasien = it))
            },
            label = { Text("NoHp") },
            isError = errorState.noPasien != null,
            placeholder = { Text("Masukkan NoHp") },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color(0xFF42A5F5),
                focusedBorderColor = Color(0xFF42A5F5),
            )
        )
        Text(text = errorState.noPasien ?: "", color = Color.Red)

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {expanded = !expanded}
        ) {
            OutlinedTextField(
                value = chosenDropdown,
                onValueChange = { },
                label = { Text("Pilih Dokter")},
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Expand Menu"
                    )
                },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                readOnly = true,
                isError = errorState.namaDokter != null
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                dokterList.forEach { dokter ->
                    DropdownMenuItem(
                        onClick = {
                            chosenDropdown = dokter.nama
                            expanded = false
                            onValueChange(jadwalEvent.copy(namaDokter = dokter.nama))
                        },
                        text = { Text(text = dokter.nama) },

                        )
                }
            }
        }
        Text(text = errorState.namaDokter ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.tanggal,
            onValueChange = {
                onValueChange(jadwalEvent.copy(tanggal = it))
            },
            label = { Text("tanggal") },
            isError = errorState.tanggal != null,
            placeholder = { Text("Masukkan tanggal") },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color(0xFF42A5F5),
                focusedBorderColor = Color(0xFF42A5F5),
            )
        )
        Text(text = errorState.tanggal ?: "", color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Status Pasien", color = Color.Black, fontWeight = FontWeight.Bold)
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            status.forEach { st ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = jadwalEvent.status == st,
                        onClick = {
                            onValueChange(jadwalEvent.copy(status = st))
                        },
                        colors = RadioButtonDefaults.colors(selectedColor = Color.Black)
                    )
                    Text(
                        text = st,
                        color = Color.Black
                    )
                }
            }
        }
        Text(text = errorState.status ?: "", color = Color.Red)
    }
}