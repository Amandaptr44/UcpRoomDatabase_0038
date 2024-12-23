package com.example.ucp2pam.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.data.entity.Dokter
import com.example.ucp2pam.ui.customwidget.TopAppBarp
import com.example.ucp2pam.ui.viewmodel.DokterViewModel
import com.example.ucp2pam.ui.viewmodel.HomeDokterViewModel
import com.example.ucp2pam.ui.viewmodel.HomeUiState
import com.example.ucp2pam.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeDokterView(
    viewModel: HomeDokterViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDokter: () -> Unit = { },
    onBack: () -> Unit = { },
    modifier : Modifier = Modifier
) {
    Scaffold (
        topBar = {
            TopAppBarp(
                judul = "Daftar Dokter",
                showBackButton = true,
                onBack = onBack,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddDokter,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Dokter",
                )
            }
        }
    ) { innerPadding ->
        val homeUiState by viewModel.homeUiState.collectAsState()

        BodyHomeDokterView(
            homeUiState = homeUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun BodyHomeDokterView(
    homeUiState: HomeUiState,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeUiState.isLoading -> {
            //Menampilkan indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            //Menampilkan pesan error
            LaunchedEffect (homeUiState.errorMessage){
                homeUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message) //Tampilkan snackbar
                    }
                }
            }
        }

