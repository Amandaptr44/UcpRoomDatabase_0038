package com.example.ucp2pam.repository

import android.adservices.adid.AdId
import com.example.ucp2pam.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

interface RepositoryJadwal {
    suspend fun insertJadwal(jadwal: Jadwal)
    fun getAllJadwal(): Flow<List<Jadwal>>
    fun getJadwal(jadwalId: Long): Flow<Jadwal>
    suspend fun updateJadwal(jadwal: Jadwal)
    suspend fun deleteJadwal(jadwal: Jadwal)
}