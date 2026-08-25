package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingStatus

data class GradingSessionResponse(
    val gradingRecordId: Long,
    val status: GradingStatus
) {
    companion object {
        fun from(gradingRecord: GradingRecord) = GradingSessionResponse(
            gradingRecordId = gradingRecord.id!!,
            status = gradingRecord.status
        )
    }
}
