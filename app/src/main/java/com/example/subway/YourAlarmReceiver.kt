package com.example.subway

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class YourAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "도착 시간입니다.", Toast.LENGTH_LONG).show()
    }
}