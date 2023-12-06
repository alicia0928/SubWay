package com.example.subway

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class LoginActivity : AppCompatActivity() {

    lateinit var edit_id: EditText
    lateinit var edit_pw: EditText
    lateinit var btn_login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        btn_login = findViewById<Button>(R.id.btn_login)
        edit_id = findViewById<EditText>(R.id.edit_id)
        edit_pw = findViewById<EditText>(R.id.edit_pw)

        btn_login.setOnClickListener {
            val enteredId = edit_id.text.toString()
            val enteredPw = edit_pw.text.toString()

            if (enteredId.isNotEmpty() && enteredPw.isNotEmpty()) {
                loginUser(enteredId, enteredPw)
            } else {
                Toast.makeText(this, "사용자 이름과 비밀번호를 모두 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        val url = "http://10.0.2.2/login_ok.php"

        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                // 변경: 서버 응답에서 경고 메시지를 로깅
                Log.d("ResponseData", "Response from server: $response")

                // 변경: 로그인 성공 여부 확인
                if (response.contains("로그인에 성공했습니다!")) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "아이디 혹은 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error: VolleyError ->
                // 변경: 오류 메시지 로깅
                Log.e("Volley Error", "Error: ${error.message}", error)
                Toast.makeText(this, "서버와의 통신 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }) {

            override fun getBody(): ByteArray {
                val params = HashMap<String, String>()
                params["user_id"] = username
                params["user_pw"] = password

                // 추가: 안드로이드에서 전송하는 데이터 로그 출력
                Log.d("RequestData", params.toString())

                return encodeParameters(params, paramsEncoding)
            }

            private fun encodeParameters(params: Map<String, String>, paramsEncoding: String): ByteArray {
                val encodedParams = StringBuilder()
                try {
                    for ((key, value) in params) {
                        encodedParams.append(URLEncoder.encode(key, paramsEncoding))
                        encodedParams.append('=')
                        encodedParams.append(URLEncoder.encode(value, paramsEncoding))
                        encodedParams.append('&')
                    }
                    return encodedParams.toString().toByteArray(charset(paramsEncoding))
                } catch (uee: UnsupportedEncodingException) {
                    throw RuntimeException("Encoding not supported: $paramsEncoding", uee)
                }
            }
        }

        // 변경: 타임아웃 설정
        stringRequest.retryPolicy = DefaultRetryPolicy(
            0,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        requestQueue.add(stringRequest)
    }
}


