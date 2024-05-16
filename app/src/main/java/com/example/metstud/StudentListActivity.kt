package com.example.metstud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
    private val ADD_ACTIVITY_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student)

        //isi data yang akan ditampilkan (array)
        imageList = arrayOf(
            R.drawable.profile2, //showcase page, my profile all about
            R.drawable.profile
        )

        nameList = arrayOf(
            "Annisa Putri Aprilia", //showcase page, my profile all about
            "Andi Premasa",
            "Budi Anda",
            "Caca Mana",
            "Danang",
            "Mantap Jiwa",
            "Banu",
        )

        nimList = arrayOf(
            "231401036",
            "241401340",
            "236392595",
            "248901819",
            "239810984",
            "233825837",
            "243987529",
        )

        emailList = arrayOf(
            "annisaputri@gmail.com",
            "user1@gmail.com",
            "user2@gmail.com",
            "user3@gmail.com",
            "user4@gmail.com",
            "user5@gmail.com",
            "user6@gmail.com",
        )
        angkatanList = arrayOf(
            23,
            24,
            23,
            24,
            23,
            23,
            24,
        )
        fakultasList = arrayOf(
            "Fasilkom-Ti",
            "Fakultas Ekonomi dan Bisnis",
            "Fakultas Ilmu Budaya",
            "Fakultas Kedokteran",
            "Fakultas Teknik",
            "Fakultas Ilmu Sosial dan Politik",
            "Fakultas Hukum",
            "Fakultas Kesehatan Masyarakat",
        )
        prodiList = arrayOf(
            "Ilmu Komputer",
            "Kewirausahaan",
            "Sastra Inggris",
            "Pendidikan Dokter",
            "Teknik Mesin",
            "Ilmu Komunikasi",
            "Hukum",
            "Gizi",
        )
        semesterList = arrayOf(
            3,
            1,
            3,
            1,
            3,
            3,
            1,

        )
        statusList = arrayOf(
            "Mahasiswa Aktif",
            "Mahasiswa Aktif",
            "Mahasiswa Aktif",
            "Mahasiswa Aktif",
            "Mahasiswa Aktif",
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


        val addButton: FloatingActionButton = findViewById(R.id.fab)
        addButton.setOnClickListener {
            val intent = Intent(this, addActivity::class.java)
            startActivityForResult(intent, ADD_ACTIVITY_REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val name = data?.getStringExtra("name")
            val nim = data?.getStringExtra("nim")
            val email = data?.getStringExtra("email")
            val angkatan = data?.getStringExtra("angkatan")
            val fakultas = data?.getStringExtra("fakultas")
            val prodi = data?.getStringExtra("prodi")
            val semester = data?.getStringExtra("semester")
            val status = data?.getStringExtra("status")
            val imageUri = data?.getStringExtra("image")

            val newData = studentData(
                R.drawable.profile,
                name.toString(),
                nim.toString(),
                email.toString(),
                angkatan.toString().toInt(),
                fakultas.toString(),
                prodi.toString(),
                semester.toString().toInt(),
                status.toString()
            )
            dataList.add(newData)
            recyclerView.adapter?.notifyDataSetChanged()
        }

    }

}
