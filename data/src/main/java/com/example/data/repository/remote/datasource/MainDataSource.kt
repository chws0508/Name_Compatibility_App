package com.example.data.repository.remote.datasource

import com.example.data.remote.model.DataLoveResponse
import com.example.domain.utils.RemoteErrorEmitter
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot

interface MainDataSource {
    suspend fun checkLoveCalculator(
        remoteErrorEmitter: RemoteErrorEmitter,
        host: String,
        key: String,
        mName: String,
        wName: String
    ): DataLoveResponse?

    //통계 가져오기
     fun getStatistics() : Task<DataSnapshot>

    //통계 저장하기
     fun setStatistics(plusValue: Int) : Task<Void>
}