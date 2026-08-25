package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingStatus
import io.swagger.v3.oas.annotations.media.Schema

data class GradingSessionResponse(
    @field:Schema(description = "채점 기록 ID", example = "1")
    val gradingRecordId: Long,

    @field:Schema(description = "채점 상태 (IN_PROGRESS 또는 COMPLETED)", example = "IN_PROGRESS")
    val status: GradingStatus
) {
    companion object {
        fun from(gradingRecord: GradingRecord) = GradingSessionResponse(
            gradingRecordId = gradingRecord.id!!,
            status = gradingRecord.status
        )
    }
}
