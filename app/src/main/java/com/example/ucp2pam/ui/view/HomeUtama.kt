package com.example.ucp2pam.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.R
import com.example.ucp2pam.data.entity.Dokter
import com.example.ucp2pam.ui.navigation.AlamatNavigasi
import com.example.ucp2pam.ui.viewmodel.DokterViewModel

object DestinasiHome : AlamatNavigasi {
    override val route = "Homeutama"
}
@Composable
fun HomeUtamaview(
    onDokter: () -> Unit,
    onJadwal: () -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(
                    id = R.color.white
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(
                id = R.drawable.umy
            ),
            contentDescription = "",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = {
                onDokter ()

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
        ) {
            Text(text = "Dokter")
        }
        Button(
            onClick = {
                onJadwal ()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
        ) {
            Text(text = "Jadwal")
        }
    }
}
