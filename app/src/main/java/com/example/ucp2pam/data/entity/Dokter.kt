package com.example.ucp2pam.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dokter")
data class Dokter (
    @PrimaryKey
    val namaPasien: String,
    val noPasien: String,
    val dokter: String,
    val tanggal: String,
    val status: String,
)
