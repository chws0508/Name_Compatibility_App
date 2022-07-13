package com.example.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentResultBinding
import com.example.presentation.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*


class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    override fun init() {
        binding.fragment = this
        initResult()
        saveScore()
    }

    private fun initResult() {
        binding.score.text = mainViewModel.apiCallResult.percentage.toString()
        when (mainViewModel.apiCallResult.percentage) {
            in 0..20 -> setLoveMsgText("조금 힘들어 보여요")
            in 21..40 -> setLoveMsgText("노력해 보세요")
            in 41..60 -> setLoveMsgText("기대해도 좋겠는데요?")
            in 61..80 -> setLoveMsgText("축하드려요!!")
            in 81..99 -> setLoveMsgText("와우... 눈을 의심하고 있어요")
            100 -> setLoveMsgText("완벽해요!!!")
            else -> setLoveMsgText("오류")
        }
        saveStatistics()
    }

    private fun saveStatistics() {
        mainViewModel.getStatistics()
            .addOnSuccessListener {
                if (it != null) mainViewModel.setStatistics(it.value.toString().toInt() + 1)
                    .addOnFailureListener {
                        error()
                    }
            }
            .addOnFailureListener {
                error()
            }
    }

    private fun saveScore()= with(mainViewModel.apiCallResult){
        mainViewModel.setScore( this.fname,this.sname,this.percentage,nowTime())
    }

    private fun nowTime():String=SimpleDateFormat("yyyy년 MM월 dd일 mm분", Locale("ko","KR")).format(
        Date(System.currentTimeMillis())
    )

    private fun error() = shortShowToast("통계를 저장하는데 오류가 생김")

    private fun setLoveMsgText(msg: String) = binding.message.setText(msg)

    fun backToMainBtnClick(view: View) {
        this.findNavController().navigate(R.id.action_resultFragment_to_mainFragment)
    }


}