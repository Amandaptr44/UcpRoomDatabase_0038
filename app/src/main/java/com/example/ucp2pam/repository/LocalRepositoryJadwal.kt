package com.example.ucp2pam.repository

import com.example.ucp2pam.data.dao.JadwalDao
import com.example.ucp2pam.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

class LocalRepositoryJadwal (private val jadwalDao: JadwalDao) : RepositoryJadwal{
    override suspend fun insertJadwal(jadwal: Jadwal) {
        jadwalDao.insertJadwal(jadwal)
    }

    override fun getAllJadwal(): Flow<List<Jadwal>> {
        return jadwalDao.getAllJadwal()
    }

    override fun getJadwal(jadwalId: Long): Flow<Jadwal> {
        return jadwalDao.getJadwal(jadwalId)
    }

    override suspend fun updateJadwal(jadwal: Jadwal) {
        jadwalDao.updateJadwal(jadwal)
    }

    override suspend fun deleteJadwal(jadwal: Jadwal) {
        jadwalDao.deleteJadwal(jadwal)
    }
}
