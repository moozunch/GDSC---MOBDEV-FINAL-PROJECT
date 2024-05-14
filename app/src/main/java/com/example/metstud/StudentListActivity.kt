package com.example.metstud

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentListActivity : AppCompatActivity() {

    //deklarasi variable
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<studentData>
    lateinit var imageList: Array<Int>
    lateinit var nameList: Array<String>
    lateinit var nimList: Array<String>
    lateinit var emailList: Array<String>
    lateinit var angkatanList: Array<Int>
    lateinit var fakultasList: Array<String>
    lateinit var prodiList: Array<String>
    lateinit var semesterList: Array<Int>
    lateinit var statusList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student)

        //isi data yang akan ditampilkan (array)
        imageList = arrayOf(
            R.drawable.profile2,
            R.drawable.profile
        )

        nameList = arrayOf(
            "Annisa Putri Aprilia",
            "Desta Rizky Putra"
        )

        nimList = arrayOf(
            "231401036",
            "241401340"
        )

        emailList = arrayOf(
            "annisaputri@gmail.com",
            "destarizky@gmail.com",
        )
        angkatanList = arrayOf(
            23,
            24,
        )
        fakultasList = arrayOf(
            "Fasilkom-Ti",
            "Fakultas Ekonomi dan Bisnis",
        )
        prodiList = arrayOf(
            "Ilmu Komputer",
            "Kewirausahaan",
        )
        semesterList = arrayOf(
            3,
            1,
        )
        statusList = arrayOf(
            "Mahasiswa Aktif",
            "Mahasiswa Aktif",
        )

        recyclerView = findViewById(R.id.studentlist)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        dataList = arrayListOf<studentData>()
        getData()

        // Create AdapterClass instance and set onItemClick
        val adapter = AdapterClass(dataList, object : AdapterClass.OnItemClickListener {
            override fun onItemClick(data: studentData) {
                val intent = Intent(this@StudentListActivity, detailActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter
    }

    //mengambil data dari array dan memasukkannya ke dalam dataList
    private fun getData(){
        for (i in emailList.indices){
            val data = studentData(
                imageList[i],
                nameList[i],
                nimList[i],
                emailList[i],
                angkatanList[i],
                fakultasList[i],
                prodiList[i],
                semesterList[i],
                statusList[i]
            )
            dataList.add(data)
        }
    }
}
