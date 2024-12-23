package com.example.ucp2pam.data.dao

import androidx.compose.ui.input.pointer.PointerId
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2pam.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

@Dao
interface JadwalDao {
    @Insert
    suspend fun insertJadwal(jadwal: Jadwal)

    @Query("SELECT * FROM Jadwal ORDER BY tanggal ASC")
    fun getAllJadwal() : Flow<List<Jadwal>>

    @Query("SELECT * FROM Jadwal WHERE jadwalId = jadwalId")
    fun getJadwal(): Flow<Jadwal>

    @Delete
    suspend fun deleteJadwal(jadwal: Jadwal)

    @Update
    suspend fun updateJadwal(jadwal: Jadwal)
}

