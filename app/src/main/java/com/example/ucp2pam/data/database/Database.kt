package com.example.ucp2pam.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2pam.data.dao.DokterDao
import com.example.ucp2pam.data.dao.JadwalDao
import com.example.ucp2pam.data.entity.Dokter
import com.example.ucp2pam.data.entity.Jadwal

@Database(entities = [Dokter::class, Jadwal::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun DokterDao(): DokterDao
    abstract fun JadwalDao(): JadwalDao

    companion object{
        @Volatile
        private var instance: Database? = null

        fun getDatabase(context: Context): Database{
            return instance ?: synchronized(this){
                Room.databaseBuilder(
                     context.applicationContext,
                    Database::class.java,
                    "Database"
                )
                    .build().also { { instance = it} }
            }

        }
    }
    }

