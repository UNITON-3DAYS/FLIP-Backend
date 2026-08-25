package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingStatus

data class GradingRecordStatusResponse(
    val status: GradingStatus
) {
    companion object {
        fun from(gradingRecord: GradingRecord) = GradingRecordStatusResponse(
            status = gradingRecord.status
        )
    }
}
