package com.example.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.viewmodel.MainViewModel


class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val mainViewModel by activityViewModels<MainViewModel>()




    override fun init() {
        binding.fragment=this
        mainViewModel.getStatisticsDisply()
        observeViewModel()

    }

    fun startBtnClick(view: View){
        this.findNavController().navigate(R.id.action_mainFragment_to_womanNameFragment)

    }

    private fun observeViewModel(){
        mainViewModel.getStatisticsEvent.observe(this){
            binding.statistics.text=it.toString()
        }
    }


}