package com.example.subway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavi:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavi = findViewById(R.id.Amain_bnv) as BottomNavigationView

        initBottomNavigation()
    }

    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragment, HomeFragment())
            .commitAllowingStateLoss()

        bottomNavi.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.bnv_pose -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bnv_camera -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, AlarmFragmnet())
                        .commitAllowingStateLoss()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bnv_home -> {
                }
                R.id.bnv_board -> {

                }
            }
            false
        }
    }
}
