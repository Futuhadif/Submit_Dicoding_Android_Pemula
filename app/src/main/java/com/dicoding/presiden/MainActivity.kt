package com.dicoding.presiden

import MainViewModel
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvPresident:RecyclerView
    private lateinit var rvCalonPresident:RecyclerView
    private val list = ArrayList<Presiden>()
    private val listCalon = ArrayList<Presiden>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(300)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        rvPresident = findViewById(R.id.rv_presiden)
        rvPresident.setHasFixedSize(true)

        rvCalonPresident = findViewById(R.id.rv_calon_presiden)
        rvCalonPresident.setHasFixedSize(true)

        if (savedInstanceState == null) {
            list.addAll(getListPresiden())
            listCalon.addAll(getListCalonPresiden())
        } else {
            list.addAll(savedInstanceState.getParcelableArrayList("list") ?: emptyList())
            listCalon.addAll(savedInstanceState.getParcelableArrayList("listCalon") ?: emptyList())
        }
        showListPresiden()
        showListCalonPresiden()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("list", ArrayList(list))
        outState.putParcelableArrayList("listCalon", ArrayList(listCalon))
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun getListPresiden(): ArrayList<Presiden> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataTanggalLahir = resources.getStringArray(R.array.data_tanggal_lahir_presiden)
        val dataMasaJabatan = resources.getStringArray(R.array.data_masa_jabatan)
        val dataLatarBelakang = resources.getStringArray(R.array.data_latar_belakang_presiden)
        val listPresiden = ArrayList<Presiden>()
        for (i in dataName.indices){
            val presiden = Presiden(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1), dataTanggalLahir[i], dataMasaJabatan[i],dataLatarBelakang[i])
            listPresiden.add(presiden)
        }
        return listPresiden
    }

    private fun showListPresiden(){
//        val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        rvPresident.layoutManager = LinearLayoutManager(this)
        val listPresidenAdapter = ListPresidenAdapter(list)
        rvPresident.adapter = listPresidenAdapter

        listPresidenAdapter.setOnItemClickCallback(object : ListPresidenAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Presiden) {
                    val presiden : Presiden= data;
                    val intent = Intent(this@MainActivity, DetailPresiden::class.java)
                    intent.putExtra(DetailPresiden.EXTRA_PRESIDEN, presiden)
                    startActivity(intent)
            }
        })
    }

    private fun getListCalonPresiden(): ArrayList<Presiden> {
        val dataName = resources.getStringArray(R.array.data_name_calon)
        val dataDescription = resources.getStringArray(R.array.data_description_calon)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo_calon)
        val dataTanggalLahir = resources.getStringArray(R.array.data_tanggal_lahir_calon_presiden)
        val dataMasaJabatan = resources.getStringArray(R.array.data_masa_jabatan_calon)
        val dataLatarBelakang = resources.getStringArray(R.array.data_latar_belakang_calon_presiden)
        val listCalonPresiden = ArrayList<Presiden>()
        for (i in dataName.indices){
            val presiden = Presiden(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1), dataTanggalLahir[i], dataMasaJabatan[i],dataLatarBelakang[i])
            listCalonPresiden.add(presiden)
        }
        return listCalonPresiden
    }


    private fun showListCalonPresiden(){
        rvCalonPresident.layoutManager = LinearLayoutManager(this)
        val listCalonPresidenAdapter = ListCalonPresidenAdapter(listCalon)
        rvCalonPresident.adapter = listCalonPresidenAdapter

        listCalonPresidenAdapter.setOnItemClickCallback(object : ListCalonPresidenAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Presiden) {
                val presiden : Presiden= data;
                val intent = Intent(this@MainActivity, DetailPresiden::class.java)
                intent.putExtra(DetailPresiden.EXTRA_PRESIDEN, presiden)
                startActivity(intent)
            }
        })
    }

}