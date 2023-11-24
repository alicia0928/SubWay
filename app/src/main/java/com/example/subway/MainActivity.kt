package com.example.subway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MenuItem
import android.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    lateinit var bottomNavi:BottomNavigationView
    lateinit var drawer_layout : DrawerLayout
    lateinit var navigation_view : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavi = findViewById(R.id.Amain_bnv) as BottomNavigationView

        initBottomNavigation()

        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        //        //setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout)
        navigation_view = findViewById<NavigationView>(R.id.navigation_view)
        navigation_view.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_home->{

            }
            R.id.item_alarm->{

            }
            R.id.item_info-> {

            }
            R.id.item_my->{

            }
        }
        return false
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
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, InformationFragment())
                        .commitAllowingStateLoss()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bnv_board -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, MypageFragment())
                        .commitAllowingStateLoss()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}
