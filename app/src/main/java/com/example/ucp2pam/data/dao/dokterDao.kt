package com.example.ucp2pam.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.ucp2pam.data.entity.Dokter
import kotlinx.coroutines.flow.Flow
import androidx.room.Query as Query

@Dao
interface dokterDao {
    @Insert
    suspend fun insertDokter(dokter: Dokter)

    @Query("SELECT * FROM dokter ORDER BY namaPasien ASC ")
    fun getAllDokter() : Flow<List<Dokter>>
}