package com.example.ucp2pam.repository

import android.adservices.adid.AdId
import com.example.ucp2pam.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

interface RepositoryDokter{
    suspend fun insertDokter(dokter: Dokter)
    fun getAllDokter(): Flow<List<Dokter>>
    fun getDokter(dokterId: Long): Flow<Dokter>
}

