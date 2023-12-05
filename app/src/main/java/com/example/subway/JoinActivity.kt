package com.example.subway

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService



class JoinActivity : AppCompatActivity() {

    lateinit var baseLayout : LinearLayout
    lateinit var edit_id_join : EditText
    lateinit var btn_overlap : Button
    lateinit var edit_pw_join : EditText
    lateinit var edit_pw_check : EditText
    lateinit var edit_name : EditText
    lateinit var spin_oper : Spinner
    lateinit var edit_phone : EditText
    lateinit var btn_next : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.join)

        baseLayout = findViewById<LinearLayout>(R.id.baseLayout)
        spin_oper = findViewById<Spinner>(R.id.spin_oper)
        btn_next = findViewById<Button>(R.id.btn_next)

        btn_next.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        spin_oper.adapter = ArrayAdapter.createFromResource(this, R.array.operList, android.R.layout.simple_spinner_item)


    }

}