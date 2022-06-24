package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DomainLoveResponse
import com.example.domain.usecase.CheckLoveCalculatorUseCase
import com.example.domain.usecase.GetStatisticsUseCase
import com.example.domain.usecase.SetStatisticsUseCase
import com.example.domain.utils.ErrorType
import com.example.domain.utils.RemoteErrorEmitter
import com.example.domain.utils.ScreenState
import com.example.presentation.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkLoveCalculatorUseCase: CheckLoveCalculatorUseCase,
    private val getStatisticsUseCase: GetStatisticsUseCase,
    private val setStatisticsUseCase: SetStatisticsUseCase,

) : ViewModel(), RemoteErrorEmitter {

    private var _apiCallEvent = SingleLiveEvent<ScreenState>()
    val apiCallEvent: LiveData<ScreenState> get() = _apiCallEvent

    private var _getStatisticsEvent = SingleLiveEvent<Int>()
    val getStatisticsEvent: LiveData<Int> get() = _getStatisticsEvent

    var apiCallResult = DomainLoveResponse("", "", 0, "")
    var apiErrorType = ErrorType.UNKNOWN
    var apiErrorTypeMessage = "none"
    var manNameResult:String ="man"
    var womanNameResult:String = "woman"


    fun checkLoveCalculator(
        host: String,
        key: String,
        mName: String,
        wName: String
    ) = viewModelScope.launch {
        checkLoveCalculatorUseCase.execute(this@MainViewModel, host, key, mName, wName)
            .let { response ->
                //반환값이 null이 아니고 잘 받아와졌을때
                if (response != null) {
                    apiCallResult = response
                    _apiCallEvent.postValue(ScreenState.LOADING)
                } else _apiCallEvent.postValue(ScreenState.ERROR)
            }

    }

    override fun onError(msg: String) {
        apiErrorTypeMessage = msg

    }

    override fun onError(errorType: ErrorType) {
        apiErrorType = errorType
    }

    fun setStatistics(plusValue: Int) = setStatisticsUseCase.execute(plusValue)

    fun getStatistics()= getStatisticsUseCase.execute()

    fun getStatisticsDisply() =getStatisticsUseCase.execute()
        .addOnSuccessListener {
            _getStatisticsEvent.postValue(it.value.toString().toInt())
        }


}