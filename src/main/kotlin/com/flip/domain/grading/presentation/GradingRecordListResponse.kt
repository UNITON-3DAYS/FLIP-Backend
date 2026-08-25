package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingRecord
import java.time.LocalDateTime

data class GradingRecordListResponse(
    val gradingRecords: List<GradingRecordSummaryResponse>
) {
    companion object {
        fun from(gradingRecords: List<GradingRecord>) = GradingRecordListResponse(
            gradingRecords = gradingRecords.map { GradingRecordSummaryResponse.from(it) }
        )
    }
}

data class GradingRecordSummaryResponse(
    val gradingRecordId: Long,
    val worksheetTitle: String,
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
