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

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<studentData>
    lateinit var imageList: Array<Int>
    lateinit var nameList: Array<String>
    lateinit var nimList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student)


        imageList = arrayOf(
            R.drawable.profile,
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

    private fun getData(){
        for (i in imageList.indices){
            val data = studentData(imageList[i], nameList[i], nimList[i])
            dataList.add(data)
        }
    }
}