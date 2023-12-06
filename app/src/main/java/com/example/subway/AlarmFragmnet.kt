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
        val total_time_TextView = view.findViewById<TextView>(R.id.textView)

        // 버튼 클릭 리스너 설정
        submit_Bu.setOnClickListener {
            submit(total_time_TextView)
        }
        return view
    }
    private fun submit(total_time_TextView: TextView) {
        var username = username_Et.text.toString()
        var password = password_Et.text.toString()
        println("username************************" + username)
        println("password************************" + password)

        var URL_ROOT = "http://10.0.2.2/login1.php?method=login&username=" + username + "&password=" + password
        // var URL_ROOT = "http://localho:8080/login1.php?method=" + loginmehtod + "&username=" + username + "&password=" + password
        //creating volley string requestw
        // Toast.makeText(this, URL_ROOT, Toast.LENGTH_LONG).show()
        val stringRequest = @SuppressLint("ApplySharedPref")

        object : StringRequest(Request.Method.GET, URL_ROOT,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)

                    var loginresponse = obj.getString("response")
                    var loginmuserid = obj.getString("total_time")

                    var loginuseridstr = loginmuserid.toString()
                    var StrResp = loginresponse.toString().trim()
                    //id = "myid"
                   // Toast.makeText(this, StrResp, Toast.LENGTH_LONG).show()
                    showToast(StrResp)
                    if(StrResp=="true") {
//                        val intent = Intent(this, StudentsActivity::class.java)
//                        startActivity(intent)
                       // Toast.makeText(this, "You are logged in", Toast.LENGTH_LONG).show()
                        //Toast.makeText(this, loginmuserid, Toast.LENGTH_LONG).show()
                        showToast("You are loggedin")
                        showToast(loginmuserid)

                        // total time을 TextView에 설정
                        total_time_TextView.text = loginmuserid // loginmuserid는 total time의 값입니다.

                        val total_time = loginmuserid.toIntOrNull()
                        if (total_time != null){
                            val delayInMillis = total_time * 60 * 1000L

                            Handler().postDelayed({
                                showToast("도착 시간입니다.")
                            }, delayInMillis)
                        } else {
                            showToast("Invalid total time format")
                        }

                    }else{
                      //  Toast.makeText(this, "Account does not exist", Toast.LENGTH_SHORT).show()
                            showToast("Account does not Exist")
                    }
                    println(StrResp + "**************************************")
                    println(loginuseridstr + "**************************************")
                } catch (e: JSONException) {
  //                  Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                    showToast(e.message.toString())
                    showToast("ERror")

                    e.printStackTrace()
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    //    Toast.makeText(applicationContext,"Error1", Toast.LENGTH_LONG).show()
                    //Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_SHORT).show()
                    if(volleyError.networkResponse==null){
                       // Toast.makeText(applicationContext,"networkresponsenull", Toast.LENGTH_LONG).show()
                        showToast("networkresponsenull")
                    }
                    if (volleyError==null){
                        showToast("volleyerrornull")
                       // Toast.makeText(applicationContext,"volleyerrornull", Toast.LENGTH_LONG).show()
                    }
                    if (volleyError == null || volleyError.networkResponse == null) {
                        return
                    }

                    val body=""
                    //get status code here
                    val statusCode = volleyError.networkResponse.statusCode
                    //get response body and parse with appropriate encoding
                    try {
                        val body = String(volleyError.networkResponse.data)
                    } catch (e: UnsupportedEncodingException) {
                        // exception
                    }
                    showToast(body)
                   // Toast.makeText(applicationContext,body, Toast.LENGTH_LONG).show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("method", "login")
                params.put("username", username)
                params.put("password", password)
                return params
            }
        }
        // adding request to queue
        MySingleton.getInstance(requireContext()).addToRequestQueue(stringRequest)
        // val queue = MySingleton.getInstance(this.applicationContext).requestQueue

    }
    private fun showToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}