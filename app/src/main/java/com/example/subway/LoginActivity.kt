package com.example.subway

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService



class LoginActivity : AppCompatActivity() {

    lateinit var edit_id : EditText
    lateinit var edit_pw : EditText
    lateinit var btn_login : Button
    lateinit var btn_find : TextView
    lateinit var btn_join : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        btn_join = findViewById<TextView>(R.id.btn_join)
        btn_login = findViewById<Button>(R.id.btn_login)
        edit_id = findViewById<EditText>(R.id.edit_id)
        edit_pw = findViewById<EditText>(R.id.edit_pw)

        btn_join.setOnClickListener{
            try {
                Thread.sleep(500) // 0.5초 동안 잠시 잠재우기
                val intent = Intent(this, JoinActivity::class.java)
                startActivity(intent)
                finish()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        btn_login.setOnClickListener{
            if (edit_id != null && edit_pw != null){
                try {
                    Thread.sleep(500) // 0.5초 동안 잠시 잠재우기
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            else{
                
            }
        }
    }
}