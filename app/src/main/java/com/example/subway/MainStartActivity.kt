package com.example.subway

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_start)

        // Thread를 사용하여 1초 대기 후에 MainActivity로 이동
        val thread = object : Thread() {
            override fun run() {
                try {
                    sleep(1000) // 1초 대기
                    val intent = Intent(this@MainStartActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish() // 현재 액티비티를 종료
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

        thread.start()
    }
}