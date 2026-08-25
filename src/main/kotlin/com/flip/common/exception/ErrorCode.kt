package com.flip.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    // Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 생겼습니다."),

    // Client Error
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "적절하지 않은 HTTP 메소드입니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "요청 값의 타입이 잘못되었습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "적절하지 않은 값입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 리소스를 찾을 수 없습니다."),
    MISSING_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "필수 파라미터가 누락되었습니다."),

    // Student
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "학생 정보를 찾을 수 없습니다."),

    // Worksheet
    WORKSHEET_NOT_FOUND(HttpStatus.NOT_FOUND, "문제지 정보를 찾을 수 없습니다."),

    // GradingRecord
    GRADING_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "채점 기록을 찾을 수 없습니다."),
    NOT_GRADING_RECORD_OWNER(HttpStatus.FORBIDDEN, "해당 채점 기록의 학생이 아닙니다."),
    GRADING_RECORD_NOT_IN_PROGRESS(HttpStatus.CONFLICT, "이미 촬영이 종료된 채점 기록입니다.");
}
