package com.example.ucp2pam.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2pam.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow


interface jadwalDao {
    @Insert
    suspend fun insertJadwal(jadwal: Jadwal)

    @Query("SELECT * FROM Jadwal ORDER BY nama ASC")
    fun getAllJadwal() : Flow<List<jadwal>>

    @Query("SELECT * FROM Jadwal WHERE noPasien = :noPasien")
    fun getJadwal(noPasien: String) : Flow<Jadwal>

    @Delete
    suspend fun deleteJadwal(jadwal: Jadwal)

    @Update
    suspend fun updateJadwal(jadwal: Jadwal)
}