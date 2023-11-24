package com.example.subway

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity

class JoinActivity : AppCompatActivity() {

    lateinit var baseLayout : LinearLayout
    lateinit var edit_id_join : EditText
    lateinit var btn_overlap : Button
    lateinit var edit_pw_join : EditText
    lateinit var edit_pw_check : EditText
    lateinit var edit_name : EditText
    lateinit var btn_oper : Button
    lateinit var edit_phone : EditText
    lateinit var btn_check : Button
    lateinit var btn_next : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.join)

        baseLayout = findViewById<LinearLayout>(R.id.baseLayout)
        btn_oper = findViewById<Button>(R.id.btn_oper) as Button
        registerForContextMenu(btn_oper)
        btn_next = findViewById<Button>(R.id.btn_next)


        btn_next.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val mInflater : MenuInflater = menuInflater
        mInflater.inflate(R.menu.phone_oper, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when(item.itemId){
            R.id.bnv_skt -> {
                btn_oper.setText("SKT")
                return true
            }
            R.id.bnv_uPlus -> {
                btn_oper.setText("LG U+")
                return true
            }
            R.id.bnv_kt -> {
                btn_oper.setText("KT")
                return true
            }
            R.id.bnv_etc -> {
                btn_oper.setText("알뜰폰")
                return true
            }
        }
        return false
    }
}