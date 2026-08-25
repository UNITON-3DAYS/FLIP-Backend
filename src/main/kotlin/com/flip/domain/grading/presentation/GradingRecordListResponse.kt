package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingRecord
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class GradingRecordListResponse(
    @field:Schema(description = "채점 기록 목록")
    val gradingRecords: List<GradingRecordSummaryResponse>
) {
    companion object {
        fun from(gradingRecords: List<GradingRecord>) = GradingRecordListResponse(
            gradingRecords = gradingRecords.map { GradingRecordSummaryResponse.from(it) }
        )
    }
}

data class GradingRecordSummaryResponse(
    @field:Schema(description = "채점 기록 ID", example = "1")
    val gradingRecordId: Long,

    @field:Schema(description = "학습지 제목", example = "중1 수학 1단원 연습문제")
    val worksheetTitle: String,

    @field:Schema(description = "채점 기록 생성 일시", example = "2026-08-25T10:00:00")
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(gradingRecord: GradingRecord) = GradingRecordSummaryResponse(
            gradingRecordId = gradingRecord.id!!,
            worksheetTitle = gradingRecord.worksheet.title,
            createdAt = gradingRecord.createdAt!!
        )
    }
}
