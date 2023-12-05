package com.example.subway

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    lateinit var bottomNavi:BottomNavigationView
    lateinit var drawer_layout : DrawerLayout
    lateinit var navigation_view : NavigationView

    //lateinit var editText: EditText
    //lateinit var editText2: EditText
    //lateinit var button: Button

    //var loginmethod = "login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //editText = findViewById(R.id.editText)
       // editText2 = findViewById(R.id.editText2)
        //button = findViewById(R.id.button)

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

       // button.setOnClickListener{
       //     submit()
       // }
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

    //private fun submit() {
     //   var startname = editText.text.toString()
     //   var arrivename = editText2.text.toString()
     //   println("startname************************" + startname)
      //  println("arrivename************************" + arrivename)

      //  var URL_ROOT = "http://10.0.2.2/login1.php?method=login&startname=" + startname + "&arrivename=" + arrivename

      //  var StringRequest = @SuppressLint("ApplySharedPref")

       // object : StringRequest(
        //    Request.Method.GET, URL_ROOT,
        //    Response.Listener<String> { response ->
       //         try{
       //             val obj = JSONObject(response)
//
        //            var subwayresponse = obj.getString("response")
         //           var subwaytotaltime = obj.getString("total_time")
//
          //          var subwaytotaltimestr = subwaytotaltime.toString()
          //          var subwayresponsestr = subwayresponse.toString().trim()

          //          Toast.makeText(this, subwayresponsestr, Toast.LENGTH_LONG).show()
              //      if (subwayresponsestr == "true"){
            //            Toast.makeText(this, "You are logged in", Toast.LENGTH_LONG).show()
             //           Toast.makeText(this, subwaytotaltime, Toast.LENGTH_LONG).show()
              //      }else{
             //           Toast.makeText(this, "Subway does noe exist", Toast.LENGTH_SHORT).show()
             //       }
             //       println(subwayresponsestr + "**************************************")
             //       println(subwaytotaltimestr + "**************************************")
             //   } catch(e: JSONException){
             //       Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
              //      Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
             //       e.printStackTrace()
            //    }
           // },
          //  object : Response.ErrorListener {
           //     override fun onErrorResponse(volleyError: VolleyError){
           //         if (volleyError.networkResponse==null){
          //              Toast.makeText(applicationContext, "networkresponsenull", Toast.LENGTH_LONG).show()
           //         }
           //         if (volleyError==null){
            //            Toast.makeText(applicationContext, "volleyerrornull", Toast.LENGTH_LONG).show()
            //        }
              //      if (volleyError == null || volleyError.networkResponse == null) {
            //            return
           //         }

           //         val body=""
           //         val statusCode = volleyError.networkResponse.statusCode
            //        try{
             //           val body = String(volleyError.networkResponse.data)
            //        } catch (e: UnsupportedEncodingException){
                        //exception
           //         }
             //       Toast.makeText(applicationContext, body, Toast.LENGTH_LONG).show()
            //    }
          //  }) {
          //  @Throws(AuthFailureError::class)
           // override fun getParams(): Map<String, String> {
          //      val params = HashMap<String, String>()
          //      params.put("method", "login")
          //      params.put("startname", startname)
          //      params.put("arrivename", arrivename)
           //     return params
           // }
      //  }
      //  MySingleton.getInstance(this).addToRequestQueue(StringRequest)

  //  }
}