@file:Suppress("DEPRECATION")

package com.example.metstud

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail) // This should be called before findViewById(...)

        val studentData = intent.getParcelableExtra<studentData>("data")
        if (studentData != null) {
            //deklrasi variable dan tipe data dengan value id nya di layout
            val textView: TextView = findViewById(R.id.name)
            val imageView: ImageView? = findViewById(R.id.profile_image) // Ensure that the view with the provided ID exists in your layout file
            val textView2: TextView = findViewById(R.id.nim)
            val textView3: TextView = findViewById(R.id.email)
            val textView4: TextView = findViewById(R.id.angkatan)
            val textView5: TextView = findViewById(R.id.fakultas)
            val textView6: TextView = findViewById(R.id.prodi)
            val textView7: TextView = findViewById(R.id.semester)
            val textView8: TextView = findViewById(R.id.status)
            val backButton: ImageView = findViewById(R.id.back)

            backButton.setOnClickListener {
                onBackPressed() //bakal balik saat ditekan
            }


            //pengisian value di layout app dengan data dari studentData yang diambil dari StudentListActivity
            if (textView != null && imageView != null) {
                textView.text = studentData.dataName
                imageView.setImageURI(Uri.parse(studentData.dataImage))
                textView2.text = studentData.dataNim
                textView3.text = studentData.dataEmail
                textView4.text = studentData.dataAngkatan.toString()
                textView5.text = studentData.dataFakultas
                textView6.text = studentData.dataProdi
                textView7.text = studentData.dataSemester.toString()
                textView8.text = studentData.dataStatus
            }
        }
    }
}