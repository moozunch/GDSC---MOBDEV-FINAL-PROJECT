package com.example.metstud

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class AboutActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)

        val instagram = findViewById<ImageView>(R.id.instagram)
        val github = findViewById<ImageView>(R.id.github)
        val linkedin = findViewById<ImageView>(R.id.linkedin)

        instagram.setOnClickListener {
            val instagramUri = Uri.parse("https://www.instagram.com/apictoresque/")
            val intent = Intent(Intent.ACTION_VIEW, instagramUri)
            startActivity(intent)
        }
        github.setOnClickListener {
            val githubUri = Uri.parse("https://github.com/moozunch")
            val intent = Intent(Intent.ACTION_VIEW, githubUri)
            startActivity(intent)
        }
        linkedin.setOnClickListener {
            val linkedinUri = Uri.parse("https://www.linkedin.com/in/annisa-putri-aprilia-7070b9299/")
            val intent = Intent(Intent.ACTION_VIEW, linkedinUri)
            startActivity(intent)
        }
        //navigation drawer - hooks
        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.nav_drawer)
        toolbar = findViewById(R.id.nav)

        //navigation drawer - toolbar
        setSupportActionBar(toolbar)

        //navigation drawer - navigation drawer menu
        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        navigationView.setCheckedItem(R.id.about_item) //default terbuka about item
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_item -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            R.id.list_item -> {
                val intent = Intent(this, StudentListActivity::class.java)
                startActivity(intent)
            }
            R.id.profile_item -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}