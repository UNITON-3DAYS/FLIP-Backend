package com.flip.domain.student.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.grading.infrastructure.GradingRecordRepository
import org.springframework.stereotype.Component

@Component
class StudentValidator(
    private val gradingRecordRepository: GradingRecordRepository
) {
    fun validateDeletable(studentId: Long) {
        if (gradingRecordRepository.existsByStudentId(studentId)) {
            throw CustomException(ErrorCode.STUDENT_HAS_GRADING_RECORDS)
        }
    }
}
