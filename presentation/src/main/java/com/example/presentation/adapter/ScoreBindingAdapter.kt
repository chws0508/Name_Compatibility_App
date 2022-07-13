package com.example.presentation.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.JsonAdapter

//xml에 속성으로 넣기 가능
object ScoreBindingAdapter {
    @JvmStatic
    @BindingAdapter("set_man")
    fun setMan(text : TextView,name:String){
        text.text=name
    }

    @JvmStatic
    @BindingAdapter("set_woman")
    fun setWoman(text: TextView,name:String){
        text.text=name
    }

    @JvmStatic
    @BindingAdapter("set_percentage")
    fun setPercentage(text: TextView,content:Int){
        text.text=content.toString()
    }
}