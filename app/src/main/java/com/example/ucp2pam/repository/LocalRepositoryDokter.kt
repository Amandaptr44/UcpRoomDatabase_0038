package com.example.ucp2pam.repository

import com.example.ucp2pam.data.dao.DokterDao
import com.example.ucp2pam.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDokter (
    private val dokterDao: DokterDao
) : RepositoryDokter {

    override suspend fun insertDokter(dokter: Dokter) {
        dokterDao.insertDokter(dokter)
    }

    //getAllDsn
    override fun getAllDokter(): Flow<List<Dokter>> {
        return dokterDao.getAllDokter()
    }

    override fun getDokter(dokter_id: String): Flow<Dokter> {
        return dokterDao.getDokter()
    }
}