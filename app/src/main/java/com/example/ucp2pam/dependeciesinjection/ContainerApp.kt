package com.example.ucp2pam.dependeciesinjection

import android.content.Context

import com.example.ucp2pam.data.database.RumahSakit
import com.example.ucp2pam.repository.LocalRepositoryDokter
import com.example.ucp2pam.repository.LocalRepositoryJadwal
import com.example.ucp2pam.repository.RepositoryDokter
import com.example.ucp2pam.repository.RepositoryJadwal

interface InterfaceContainerApp{
    val repositoryDokter: RepositoryDokter
    val repositoryJadwal: RepositoryJadwal
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDokter: RepositoryDokter by lazy {
        LocalRepositoryDokter(RumahSakit.getDatabase(context).DokterDao())
    }

    override val repositoryJadwal: RepositoryJadwal by lazy {
        LocalRepositoryJadwal(RumahSakit.getDatabase(context).JadwalDao())
    }
}