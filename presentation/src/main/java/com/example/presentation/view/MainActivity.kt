package com.example.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.viewmodel.MainViewModel
import com.pss.barlibrary.CustomBar.Companion.setTransparentBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint//view 모델 사용하기 위한 어노테이션,
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun init() {

        setTransparentBar(this)
        mainViewModel
    }
}