package com.example.ucp2pam.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jadwal")
data class Jadwal (
    @PrimaryKey
    val namaPasien: String,
    val noPasien: String,
    val namaDokter: String,
    val tanggal: String,
    val status: String,
)