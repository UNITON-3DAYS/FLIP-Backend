package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingStatus
import io.swagger.v3.oas.annotations.media.Schema

data class GradingRecordStatusResponse(
    @field:Schema(description = "채점 상태 (IN_PROGRESS 또는 COMPLETED)", example = "IN_PROGRESS")
    val status: GradingStatus
) {
    companion object {
        fun from(gradingRecord: GradingRecord) = GradingRecordStatusResponse(
            status = gradingRecord.status
        )
    }
}
