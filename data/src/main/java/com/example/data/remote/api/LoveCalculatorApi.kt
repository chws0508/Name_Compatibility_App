package com.example.data.remote.api

import com.example.data.remote.model.DataLoveResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query



interface LoveCalculatorApi {
    @GET("/getPercentage")//문서의 요청 형태 (METHOD) 부분이 GET 이기 떄문에 어노테이션 GET 을 붙임
    suspend fun getPercentage(
        @Header("X-RapidAPI-Key")key :String,//헤더부분 어노테이션 붙여서 함
        @Header("X-RapidAPI-Host")host : String,
        @Query("sname")wName : String,
        @Query("fname")mName: String//매개변수(파라미터) 부분 적기
    ) : Response<DataLoveResponse>

    //반환값을 받기 위한 Data Class 만들어줘야함
}