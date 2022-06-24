package com.example.domain.utils

//에러가 났을 때 타입
enum class ErrorType {
    //네트워크 문제
    NETWORK,
    //요청 시간 초과
    TIMEOUT,
    //세션 만료
    SESSION_EXPIRED,
    //알 수 없는 다른 문제
    UNKNOWN
}