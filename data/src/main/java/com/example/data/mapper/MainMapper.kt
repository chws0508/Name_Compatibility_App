package com.example.data.mapper

import com.example.data.remote.model.DataLoveResponse
import com.example.data.remote.model.DataScore
import com.example.domain.model.DomainDataScore
import com.example.domain.model.DomainLoveResponse

object MainMapper {

    fun loveMapper(
        dataResponse: DataLoveResponse?
    ):DomainLoveResponse?{
        return if(dataResponse !=null) {
            DomainLoveResponse(
                fname = dataResponse.fname,
                percentage = dataResponse.percentage,
                result = dataResponse.result,
                sname = dataResponse.sname
            )
        }else{
            dataResponse
        }
    }

    fun scoreMapper(
        dataScroe:DomainDataScore
    ):DataScore{
        return if(dataScroe!=null){
            DataScore(
                man = dataScroe.man,
                woman = dataScroe.woman,
                percentage = dataScroe.percentage,
                date = dataScroe.date
            )
        }else{
            dataScroe
        }
    }
}