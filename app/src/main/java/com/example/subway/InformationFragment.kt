package com.example.subway

import android.app.AlertDialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.infomation_fragment.*

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
        button2.setOnClickListener{
            showMapDialog()
        }
        return view
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