package com.example.domain.repository

import com.example.domain.model.DomainLoveResponse
import com.example.domain.utils.RemoteErrorEmitter
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot

//도메인은 데이터 계층에 대한 의존성을 가지고 있지 않기때문에 Data 모듈의 Data class 사용 불가-> 또 다른 model(data class) 을 만들어줘야함

interface MainRepository {
    suspend fun checkLoveCalculator(
        remoteErrorEmitter: RemoteErrorEmitter,
        host: String,
        key: String,
        mName: String,
        wName: String
    ): DomainLoveResponse?

    //통계 가져오기
     fun getStatistics() : Task<DataSnapshot>

    //통계 저장하기
     fun setStatistics(plusValue: Int) : Task<Void>

}