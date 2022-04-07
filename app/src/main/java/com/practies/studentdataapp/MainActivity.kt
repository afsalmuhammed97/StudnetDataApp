package com.practies.studentdataapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setupActionBarWithNavController(findNavController(R.id.fragment))
     //  supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }
        override fun onSupportNavigateUp(): Boolean {
           // onBackPressed()


            val navController= findNavController(R.id.fragment)

            return  navController.navigateUp()||  super.onSupportNavigateUp()
        }

    }

