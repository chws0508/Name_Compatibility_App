package com.example.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivitySplashBinding
import com.example.presentation.viewmodel.SplashViewModel
import com.example.presentation.widget.extension.startActivityAndFinish
import com.pss.barlibrary.CustomBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val splashViewModel by viewModels<SplashViewModel>()
    private val appversion="1.1.0"

    override fun init() {
        CustomBar.setTransparentBar(this)
        splashViewModel.checkAppVersion()
            .addOnSuccessListener {
                if(appversion==it.value){
                    startActivityAndFinish(this,MainActivity::class.java)
                }else{
                    longShowToast("앱 버젼이 다릅니다")
                }
            }
            .addOnFailureListener{
                shortShowToast("오류가 발생하였습니다 오류코드 ${it.message}")
            }

    }

}