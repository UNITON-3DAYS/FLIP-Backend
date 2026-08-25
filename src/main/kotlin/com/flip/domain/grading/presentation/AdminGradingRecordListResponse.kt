package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.worksheet.domain.WorksheetSource
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class AdminGradingRecordListResponse(
    @field:Schema(description = "채점 기록 목록")
    val gradingRecords: List<AdminGradingRecordSummaryResponse>
) {
    companion object {
        fun from(gradingRecords: List<GradingRecord>) = AdminGradingRecordListResponse(
            gradingRecords = gradingRecords.map { AdminGradingRecordSummaryResponse.from(it) }
        )
    }
}

data class AdminGradingRecordSummaryResponse(
    @field:Schema(description = "채점 기록 ID", example = "1")
    val gradingRecordId: Long,

    @field:Schema(description = "채점 기록 생성 일시", example = "2026-08-25T10:00:00")
    val createdAt: LocalDateTime,

    @field:Schema(description = "학생 이름", example = "홍길동")
    val studentName: String,

    @field:Schema(description = "학년", example = "1")
    val grade: Int,

    @field:Schema(description = "문제지 유형", example = "INHOUSE")
    val worksheetSource: WorksheetSource,

    @field:Schema(description = "문제지 타이틀", example = "중1 수학 1단원 연습문제")
    val worksheetTitle: String
) {
    companion object {
        fun from(gradingRecord: GradingRecord) = AdminGradingRecordSummaryResponse(
            gradingRecordId = gradingRecord.id!!,
            createdAt = gradingRecord.createdAt!!,
            studentName = gradingRecord.student.name,
            grade = gradingRecord.student.grade,
            worksheetSource = gradingRecord.worksheet.source,
            worksheetTitle = gradingRecord.worksheet.title
        )
    }
}
