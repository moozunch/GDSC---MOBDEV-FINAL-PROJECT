package com.example.metstud

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class StudentListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student)

        //isi data yang akan ditampilkan (array)
        imageList = arrayOf(
            R.drawable.profile2, //showcase page, my profile all about
            R.drawable.no_profile_picture_15257,
            R.drawable.no_profile_picture_15257,
            R.drawable.no_profile_picture_15257,
            R.drawable.no_profile_picture_15257,
            R.drawable.no_profile_picture_15257,
            R.drawable.no_profile_picture_15257,

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

        //navigation drawer - hooks
        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.nav_drawer)
        toolbar = findViewById(R.id.nav) //

        //navigation drawer - toolbar
        setSupportActionBar(toolbar)

        //navigation drawer - navigation drawer menu
        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }
    //agar saat ditekan balik saat drawer terbuka nggak ketutup aplikasinya
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

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
                R.drawable.profile1,
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

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return true
    }

}
