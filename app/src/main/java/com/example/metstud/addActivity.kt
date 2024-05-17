package com.example.metstud

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException
import android.Manifest
import android.util.Log

class addActivity : AppCompatActivity() {

    private var imageUri: Uri? = null
    private lateinit var profileImage: ImageView

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
        imageUri = uri
        try {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            profileImage.setImageBitmap(bitmap)
            Log.d("addActivity", "Image set successfully") // Add this line
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("addActivity", "Error setting image", e) // Add this line
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)

        profileImage = findViewById(R.id.profile_image)
        profileImage.setOnClickListener {
            pickImage.launch("image/*")
        }

        val name = findViewById<EditText>(R.id.name)
        val nim = findViewById<EditText>(R.id.nim)
        val email = findViewById<EditText>(R.id.email)
        val angkatan = findViewById<EditText>(R.id.angkatan)
        val fakultas = findViewById<EditText>(R.id.fakultas)
        val prodi = findViewById<EditText>(R.id.prodi)
        val semester = findViewById<EditText>(R.id.semester)
        val status = findViewById<EditText>(R.id.status)
        val lanjutkan = findViewById<TextView>(R.id.lanjutkan)
        val backButton = findViewById<TextView>(R.id.cancel)

        lanjutkan.setOnClickListener{
            val intent = Intent()
            intent.putExtra("image", imageUri.toString())
            intent.putExtra("name", name.text.toString())
            intent.putExtra("nim", nim.text.toString())
            intent.putExtra("email", email.text.toString())
            intent.putExtra("angkatan", angkatan.text.toString())
            intent.putExtra("fakultas", fakultas.text.toString())
            intent.putExtra("prodi", prodi.text.toString())
            intent.putExtra("semester", semester.text.toString())
            intent.putExtra("status", status.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }


        backButton.setOnClickListener {
            onBackPressed() //bakal balik saat ditekan
        }
    }
}