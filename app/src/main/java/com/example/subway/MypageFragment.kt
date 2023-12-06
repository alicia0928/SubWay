package com.example.subway

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MypageFragment : Fragment(){
    lateinit var editText5 : EditText
    lateinit var textView28 : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.mypage_fragment, container, false)

        editText5 = view.findViewById<EditText>(R.id.editText5)
        textView28 = view.findViewById<TextView>(R.id.textView28)

        textView28.text = "sungShinLover1"

        editText5.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus){
                textView28.text = editText5.text
            }
        }
        return view
    }
}