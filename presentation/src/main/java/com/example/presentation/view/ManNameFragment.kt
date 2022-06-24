package com.example.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.utils.ErrorType
import com.example.domain.utils.ScreenState
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentManNameBinding
import com.example.presentation.viewmodel.MainViewModel


class ManNameFragment : BaseFragment<FragmentManNameBinding>(R.layout.fragment_man_name) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    override fun init() {
        binding.fragment=this
        observeViewModel()
    }

    fun resultBtnClick(view: View){
        binding.loadingBar.visibility=View.VISIBLE
        mainViewModel.manNameResult=binding.EditTextManName.text.toString()
        mainViewModel.checkLoveCalculator("love-calculator.p.rapidapi.com",
            "cae972a519mshcbcda86e738a84ap18b31ajsne0b21cbb3c8e",
            mainViewModel.womanNameResult,
            mainViewModel.manNameResult)
    }

    private fun observeViewModel(){
        mainViewModel.apiCallEvent.observe(this){
            binding.loadingBar.visibility=View.INVISIBLE
            when(it){
                ScreenState.LOADING->   this.findNavController().navigate(R.id.action_manNameFragment_to_resultFragment)
                ScreenState.ERROR-> toastErrorMessage()
                else->shortShowToast("알 수 없는 오류가 발생하였습니다")
            }
        }
    }

    private fun toastErrorMessage(){
        when(mainViewModel.apiErrorType){
            ErrorType.NETWORK->longShowToast("네트워크 오류가 발생하였습니다")
            ErrorType.TIMEOUT->longShowToast("호출 시간이 초과되었습니다")
            ErrorType.SESSION_EXPIRED->longShowToast("세션이 만료되었습니다")
            ErrorType.UNKNOWN->("예기치 못한 오류가 발생하였습니다")
        }
    }

}