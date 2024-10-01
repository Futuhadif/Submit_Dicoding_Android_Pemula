package com.dicoding.presiden

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Presiden(
    val nama: String?,
    val deskripsi: String?,
    val photo: Int,
    val tanggalLahir: String,
    val masaJabatan:String,
    val latarBelakang:String
):Parcelable
