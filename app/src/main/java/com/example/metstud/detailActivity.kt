@file:Suppress("DEPRECATION")

package com.example.metstud

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
            val textView: TextView = findViewById(R.id.name)
            // Ensure that the view with the provided ID exists in your layout file
            val imageView: ImageView? = findViewById(R.id.profile_image)

            if (textView != null && imageView != null) {
                textView.text = studentData.dataName
                // imageView.setImageResource(studentData.dataImage) // Commented out as the ImageView might not exist
            }
        }
    }
}
