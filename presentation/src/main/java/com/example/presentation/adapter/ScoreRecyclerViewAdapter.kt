package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.layout.Layout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.DomainDataScore
import com.example.presentation.R
import com.example.presentation.databinding.ScoreRecyclerItmBinding
import com.example.presentation.viewmodel.MainViewModel

class ScoreRecyclerViewAdapter (
    private val viewModel: MainViewModel
        ):RecyclerView.Adapter<ScoreRecyclerViewAdapter.ScoreRecyclerViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ScoreRecyclerItmBinding>(
            layoutInflater,
            R.layout.score_recycler_itm,
            parent,
        false
        )
        return ScoreRecyclerViewHolder(binding)
    }

    //각 Item마다 bind가 호출되어 list의 position 번째에 있는 값을 보여준다.
    override fun onBindViewHolder(holder: ScoreRecyclerViewHolder, position: Int) {
       holder.bind(viewModel.scoreList[position])
    }

    override fun getItemCount(): Int {
        return viewModel.scoreList.size
    }

    inner class ScoreRecyclerViewHolder(val binding: ScoreRecyclerItmBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: DomainDataScore){
            binding.data=data
            binding.executePendingBindings()
        }

    }
}