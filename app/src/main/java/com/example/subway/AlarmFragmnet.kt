package com.example.subway

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import android.R.attr.data
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.UnsupportedEncodingException

@SuppressLint("StaticFieldLeak")
lateinit var username_Et: EditText
@SuppressLint("StaticFieldLeak")
lateinit var password_Et: EditText
@SuppressLint("StaticFieldLeak")
lateinit var submit_Bu: Button

class AlarmFragmnet : Fragment(){

    var loginmehtod = "login"
    private lateinit var total_time_TextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.alarm_fragment, container, false)
        // EditText와 Button 초기화
        username_Et = view.findViewById(R.id.editText)
        password_Et = view.findViewById(R.id.editText2)
        submit_Bu = view.findViewById(R.id.button)

        // TextView 초기화
        total_time_TextView = view.findViewById<TextView>(R.id.textView)

        // 버튼 클릭 리스너 설정
        submit_Bu.setOnClickListener {
            submit()
        }
        return view
    }
    private fun submit() {
        val board = username_Et.text.toString()
        val getoff = password_Et.text.toString()

        // 서버에 전송할 매개변수를 Map으로 정의
        val params = HashMap<String, String>()
        params["currentStation"] = board
        params["destinationStation"] = getoff

        val URL_ROOT = "http://10.0.2.2/subwayalarm_process.php?mode=subwayalram"

        val stringRequest = object : StringRequest(Request.Method.POST, URL_ROOT,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)

                    // "total_time" 키의 값을 가져옴
                    var loginmuserid = obj.getString("total_time")

                    // "total_time" 값을 사용하도록 수정
                    showToast(loginmuserid)

                    val totalMinutes = loginmuserid.toInt()// 서버에서 받은 값, 예를 들어 14분이면 14로 받아왔다고 가정합니다.

                    val minutesDisplay = totalMinutes % 60
                    val secondsDisplay = 0

                    val formattedTime = String.format("%02d:%02d", minutesDisplay, secondsDisplay)

                    total_time_TextView.text = formattedTime

                    val total_time = loginmuserid.toIntOrNull()
                    if (total_time != null) {
                        val delayInMillis = total_time * 60 * 1000L

                        Handler().postDelayed({
                            showToast("도착 시간입니다.")
                        }, delayInMillis)
                    } else {
                        showToast("Invalid total time format")
                    }
                } catch (e: JSONException) {
                    showToast("Error parsing JSON: ${e.message}")
                    e.printStackTrace()
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    if (volleyError.networkResponse == null) {
                        showToast("networkresponsenull")
                    }
                    if (volleyError == null) {
                        showToast("volleyerrornull")
                    }
                    if (volleyError == null || volleyError.networkResponse == null) {
                        return
                    }

                    val body = ""
                    val statusCode = volleyError.networkResponse.statusCode
                    try {
                        val body = String(volleyError.networkResponse.data)
                    } catch (e: UnsupportedEncodingException) {
                    }

                    showToast(body)
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                return params
            }
        }

        // Volley 요청 큐에 추가
        MySingleton.getInstance(requireContext()).addToRequestQueue(stringRequest)
    }
    private fun showToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}