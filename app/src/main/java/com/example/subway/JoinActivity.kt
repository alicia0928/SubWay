package com.example.subway

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import retrofit2.Call
import retrofit2.Callback


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

        baseLayout = findViewById(R.id.baseLayout)
        edit_id_join = findViewById(R.id.edit_id_join) // 이 부분을 추가해주세요
        btn_overlap = findViewById(R.id.btn_overlap)
        edit_pw_join = findViewById(R.id.edit_pw_join)
        edit_pw_check = findViewById(R.id.edit_pw_check)
        edit_name = findViewById(R.id.edit_name)
        spin_oper = findViewById(R.id.spin_oper)
        edit_phone = findViewById(R.id.edit_phone)
        btn_next = findViewById(R.id.btn_next)

        btn_next.setOnClickListener {
            val id = edit_id_join.text.toString()
            val pw = edit_pw_join.text.toString()
            val pwCheck = edit_pw_check.text.toString()
            val name = edit_name.text.toString()
            val phone = edit_phone.text.toString()

            // 서버에 전송할 매개변수를 Map으로 정의
            val params = HashMap<String, String>()
            params["userid"] = id
            params["pw1"] = pw
            params["name"] = name
            params["tel"] = phone

            // POST 요청을 보낼 URL
            val url = "http://10.0.2.2/signup_process.php?mode=register"

            // Volley의 StringRequest를 사용하여 POST 요청 생성
            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                object : Response.Listener<String> {
                    override fun onResponse(response: String?) {
                        Log.d("JoinActivity1", response.toString())
                        if (response?.trim() == "success") {
                            Log.d("JoinActivity2", "success")
                            val intent = Intent(this@JoinActivity, ProfileActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Log.d("JoinActivity3", "error: $response")
                            val intent = Intent(this@JoinActivity, ProfileActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(volleyError: VolleyError) {
                        Log.d("JoinActivity4", volleyError.toString())
                    }
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    return params
                }
            }
            Volley.newRequestQueue(this).add(stringRequest)
        }

        spin_oper.adapter = ArrayAdapter.createFromResource(this, R.array.operList, android.R.layout.simple_spinner_item)


    }

}