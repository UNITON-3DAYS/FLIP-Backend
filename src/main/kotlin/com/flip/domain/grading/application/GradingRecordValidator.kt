package com.flip.domain.grading.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingStatus
import org.springframework.stereotype.Component

@Component
class GradingRecordValidator {

    fun validateOwner(gradingRecord: GradingRecord, studentId: Long) {
        if (gradingRecord.student.id != studentId) {
            throw CustomException(ErrorCode.NOT_GRADING_RECORD_OWNER)
        }
    }

    fun validateInProgress(gradingRecord: GradingRecord) {
        if (gradingRecord.status != GradingStatus.IN_PROGRESS) {
            throw CustomException(ErrorCode.GRADING_RECORD_NOT_IN_PROGRESS)
        }
    }
}
