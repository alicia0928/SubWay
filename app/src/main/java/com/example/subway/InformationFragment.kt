package com.example.subway

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.infomation_fragment.*
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.util.Collections.replaceAll

class InformationFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.infomation_fragment, container, false)

        // Spinner에 연결할 문자열 배열 가져오기
        val spinnerItems = resources.getStringArray(R.array.my_array)

        // Spinner 찾기
        val spinner = view.findViewById<Spinner>(R.id.spn_SPList)

        // ArrayAdapter를 사용하여 기본 스피너 레이아웃을 사용하도록 지정
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerItems)

        // 선택 목록이 나타날 때 사용할 레이아웃 지정
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // 어댑터를 스피너에 적용
        spinner.adapter = adapter

        val button2: Button = view.findViewById(R.id.button2)
        val button3: Button = view.findViewById(R.id.button3)
        button2.setOnClickListener{
            showMapDialog()
        }
        button3.setOnClickListener{
            submit()
        }
        return view
    }
    private fun submit() {
        var edittext1 = editText1.text.toString()
        //var dropdown = spn_SPList.toString()
        val dropdown = spn_SPList.selectedItem.toString()
        println("username************************" + edittext1)
        println("password************************" + dropdown)

        var URL_ROOT = "http://10.0.2.2/subwayinformation_process1.php?destinationStation=" + edittext1+ "&direction=" + dropdown
        // var URL_ROOT = "http://localho:8080/login1.php?method=" + loginmehtod + "&username=" + username + "&password=" + password
        //creating volley string requestw
        // Toast.makeText(this, URL_ROOT, Toast.LENGTH_LONG).show()
        val stringRequest = @SuppressLint("ApplySharedPref")

        object : StringRequest(
            Request.Method.GET, URL_ROOT,
            Response.Listener<String> { response ->
                try {
                 //   var output= Html.fromHtml(response).toString();
//                    var first = output.indexOf("{");
//                    var  second = output.indexOf("{", first + 1);
//                    var start = output.indexOf("<div");
//                    var end = output.indexOf("</div>") + 1;
                //    output = output.substring(start, end);

                    //이거 토스트로 보여주는거
                    //showToast(response.toString())
                   // var output= Html.fromHtml(response).toString();
//                    output=output.substring(output.indexOf("{"),output.IndexOf("}"));
                    val obj = JSONObject(response)

                    var loginresponse = obj.getString("response")
                    var dochakyeo = obj.getString("dochakyeo")
                    var direction = obj.getString("direction")
                    var hachabanghyang = obj.getString("hachabanghyang")
                    var hwajangsilyumu = obj.getString("hwajangsilyumu")
                    var hachajungbo = obj.getString("hachajungbo")
                    var chutcha = obj.getString("chutcha")
                    var makcha = obj.getString("makcha")

                    var StrResp = loginresponse.toString().trim()
                    //id = "myid"
                    // Toast.makeText(this, StrResp, Toast.LENGTH_LONG).show()
                    //이거 true 토스트 나타내는 거
                    //showToast(StrResp)
                    if(StrResp=="true") {
//                        val intent = Intent(this, StudentsActivity::class.java)
//                        startActivity(intent)
                        // Toast.makeText(this, "You are logged in", Toast.LENGTH_LONG).show()
                        //Toast.makeText(this, loginmuserid, Toast.LENGTH_LONG).show()
                        //showToast("You are loggedin")
                        //showToast(makcha)
                        textView9.text = "$hachabanghyang"
                        textView11.text = "$hwajangsilyumu"
                        textView12.text = "$hachajungbo"
                        textView17.text = "$chutcha"
                        textView19.text = "$makcha"
                       // resultTextView.text = "도착역: $dochakyeo\n상행/하행: $direction\n하차방향: $hachabanghyang\n화장실유무: $hwajangsilyumu\n빠른 하차 정보: $hachajungbo\n첫차 정보: $chutcha\n막차 정보: $makcha"
                    }else{
                        //  Toast.makeText(this, "Account does not exist", Toast.LENGTH_SHORT).show()
                        showToast("Account does not Exist")
                    }
                    println(StrResp + "**************************************")

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
                params.put("username", edittext1)
                params.put("password", dropdown)
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
    private fun showMapDialog(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("4호선 노선도")

        val imageView = ImageView(requireContext())
        val drawable: Drawable? = requireContext().resources.getDrawable(R.drawable.subway4line)
        imageView.setImageDrawable(drawable)

        builder.setView(imageView)

        builder.setPositiveButton("닫기") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}