package com.example.ucp2pam.ui.customwidget

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ucp2pam.data.entity.Dokter

import androidx.compose.material3.CardDefaults

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DokterListScreen(dokters: List<Dokter>){
    Scaffold (
        topBar = {
            TopAppBar(title = { Text("Daftar Dokter") })
        }
    ){
        LazyColumn (modifier = Modifier.fillMaxSize()) {
            items(dokters) { dokter ->
                DokterItem(dokter)
            }
        }
    }
}

@Composable
fun DokterItem(dokter: Dokter) {
    val backgroundColor = getColorForSpesialis(dokter.spesialis)

    Card(
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { },
    ){
        Column (modifier = Modifier.padding(16.dp)) {
            Text(text = "Nama: ${dokter.nama}")
            Text(text = "Spesialis: ${dokter.spesialis}")
            Text(text = "Klinik: ${dokter.klinik}")
            Text(text = "No.Hp: ${dokter.noHp}")
            Text(text = "Jam Kerja: ${dokter.jamKerja}")
        }
    }
}

@Composable
fun getColorForSpesialis(spesialis: String): Color {
    return when (spesialis) {
        "Umum" -> Color.LightGray
        "Anak" -> Color.Blue
        "Gigi" -> Color.Yellow
        "Kandungan" -> Color.Magenta
        "Penyakit Dalam" -> Color.Green
        else -> Color.Red
    }
}


