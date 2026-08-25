package com.flip.domain.grading.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingStatus
import com.flip.domain.grading.infrastructure.GradingRecordRepository
import org.springframework.stereotype.Component

@Component
class GradingRecordReader(
    private val gradingRecordRepository: GradingRecordRepository
) {
    fun getById(gradingRecordId: Long): GradingRecord {
        return gradingRecordRepository.findById(gradingRecordId)
            .orElseThrow { CustomException(ErrorCode.GRADING_RECORD_NOT_FOUND) }
    }

    fun findAllCompletedByStudentId(studentId: Long): List<GradingRecord> {
        return gradingRecordRepository.findAllByStudentIdAndStatusOrderByCreatedAtDesc(studentId, GradingStatus.COMPLETED)
    }
}
