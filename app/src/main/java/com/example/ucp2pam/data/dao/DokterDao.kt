package com.example.ucp2pam.data.dao

import android.adservices.adid.AdId
import androidx.room.Dao
import androidx.room.Insert
import com.example.ucp2pam.data.entity.Dokter
import com.example.ucp2pam.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow
import androidx.room.Query as Query

@Dao
interface DokterDao {
    @Insert
    suspend fun insertDokter(dokter: Dokter)

    @Query("SELECT * FROM dokter ORDER BY nama ASC")
    fun getAllDokter() : Flow<List<Dokter>>

    @Query("SELECT * FROM dokter WHERE dokter_id = dokter_id")
    fun getDokter(): Flow<Dokter>
}