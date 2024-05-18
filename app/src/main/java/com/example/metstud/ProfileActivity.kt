package com.example.metstud

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ProfileActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val instagramTb = findViewById<ImageView>(R.id.oodj8e)

        instagramTb.setOnClickListener {
            val instagramUri = Uri.parse("https://www.instagram.com/p/CjE1xL8PLTg/")
            val intent = Intent(Intent.ACTION_VIEW, instagramUri)
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

        navigationView.setCheckedItem(R.id.profile_item) //default terbuka profile item

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
            R.id.profile_item -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            R.id.list_item -> {
                val intent = Intent(this, StudentListActivity::class.java)
                startActivity(intent)
            }
            R.id.about_item -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}