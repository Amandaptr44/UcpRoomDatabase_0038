package com.example.ucp2pam.data.dao

import androidx.compose.ui.input.pointer.PointerId
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2pam.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow


interface JadwalDao {
    @Insert
    suspend fun insertJadwal(jadwal: Jadwal)

    @Query("SELECT * FROM Jadwal ORDER BY tanggal ASC")
    fun getAllJadwal() : Flow<List<Jadwal>>

    @Query("SELECT * FROM Jadwal WHERE jadwal_id = :jadwalId")
    fun getJadwal(jadwalId: Long): Flow<Jadwal>

    @Delete
    suspend fun deleteJadwal(jadwal: Jadwal)

