package com.dicoding.presiden

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailPresiden : AppCompatActivity() {

    companion object{
        const val EXTRA_PRESIDEN = "extra_presiden"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_presiden)

        val tvObject: TextView = findViewById(R.id.tv_nama_presiden)
        val tvDeskripsi : TextView = findViewById(R.id.tv_deskripsi_objek)
        val imgObject : ImageView = findViewById(R.id.img_foto_presiden)
        val tvLahir : TextView = findViewById(R.id.tv_tgl)
        val tvMasaJabatan : TextView = findViewById(R.id.tv_masa_jabatan_content)
        val tvLatarBelakang : TextView = findViewById(R.id.tv_latar_belakang_content)

        val presiden = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Presiden>(EXTRA_PRESIDEN, Presiden::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Presiden>( EXTRA_PRESIDEN)
        }

        if(presiden != null){
            val text = "Name : ${presiden.nama.toString()}"
            tvObject.text = text
            tvDeskripsi.text = presiden.deskripsi
            imgObject.setImageResource(presiden.photo)
            tvLahir.text = presiden.tanggalLahir
            tvMasaJabatan.text = presiden.masaJabatan
            tvLatarBelakang.text = presiden.latarBelakang
        }
    }
}