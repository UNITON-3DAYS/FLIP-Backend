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
    FILE_SIZE_EXCEEDED(HttpStatus.PAYLOAD_TOO_LARGE, "업로드 가능한 파일 크기를 초과했습니다."),

    // School
    SCHOOL_NOT_FOUND(HttpStatus.NOT_FOUND, "학교 정보를 찾을 수 없습니다."),
    SCHOOL_HAS_STUDENTS(HttpStatus.CONFLICT, "소속 학생이 있어 학교를 삭제할 수 없습니다."),

    // Student
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "학생 정보를 찾을 수 없습니다."),
    STUDENT_HAS_GRADING_RECORDS(HttpStatus.CONFLICT, "채점 기록이 있어 학생을 삭제할 수 없습니다."),

    // Worksheet
    WORKSHEET_NOT_FOUND(HttpStatus.NOT_FOUND, "문제지 정보를 찾을 수 없습니다."),
    WORKSHEET_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 문제지입니다."),
    WORKSHEET_HAS_REFERENCES(HttpStatus.CONFLICT, "참조 중인 데이터가 있어 문제지를 삭제할 수 없습니다."),

    // Question
    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "문항 정보를 찾을 수 없습니다."),
    QUESTION_ALREADY_EXISTS(HttpStatus.CONFLICT, "같은 페이지에 동일한 번호의 문항이 이미 존재합니다."),

    // GradingRecord
    GRADING_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "채점 기록을 찾을 수 없습니다."),
    NOT_GRADING_RECORD_OWNER(HttpStatus.FORBIDDEN, "해당 채점 기록의 학생이 아닙니다."),
    GRADING_RECORD_NOT_IN_PROGRESS(HttpStatus.CONFLICT, "이미 촬영이 종료된 채점 기록입니다."),
    GRADING_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 채점 기록에 속한 이미지가 아닙니다.");
}
