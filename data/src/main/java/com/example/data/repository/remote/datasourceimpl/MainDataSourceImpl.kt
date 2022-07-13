package com.example.data.repository.remote.datasourceimpl

import com.example.data.remote.api.LoveCalculatorApi
import com.example.data.remote.model.DataLoveResponse
import com.example.data.remote.model.DataScore
import com.example.data.repository.remote.datasource.MainDataSource
import com.example.data.utils.BaseDataSource
import com.example.domain.utils.RemoteErrorEmitter
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(
    private val loveCalculatorApi: LoveCalculatorApi,
    private val firebaseRtdb: FirebaseDatabase,
    private val fireStore: FirebaseFirestore
) : BaseDataSource(), MainDataSource {
    override suspend fun checkLoveCalculator(
        remoteErrorEmitter: RemoteErrorEmitter,
        host: String,
        key: String,
        wName: String,
        mName: String
    ) :DataLoveResponse?{
        return safeApiCall(remoteErrorEmitter){
            loveCalculatorApi.getPercentage(host = host, key = key, wName = wName, mName = mName)
        }?.body()
    }

    override fun getStatistics(): Task<DataSnapshot> {
        return firebaseRtdb.reference.child("statistics").get()
    }

    override fun setStatistics(plusValue: Int): Task<Void> {
        return firebaseRtdb.reference.child("statistics").setValue(plusValue)
    }

    override fun getScore(): Task<QuerySnapshot> {
        return fireStore.collection("score").orderBy("date", Query.Direction.DESCENDING).get()
    }

    override fun setScore(dataScore: DataScore): Task<Void> {
        return fireStore.collection("score").document().set(dataScore)
    }

}
