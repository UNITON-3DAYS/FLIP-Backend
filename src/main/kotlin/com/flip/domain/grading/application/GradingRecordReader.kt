package com.flip.domain.grading.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingStatus
import com.flip.domain.grading.infrastructure.GradingRecordRepository
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class GradingRecordReader(
    private val gradingRecordRepository: GradingRecordRepository
) {
    fun getById(gradingRecordId: Long): GradingRecord {
        return gradingRecordRepository.findById(gradingRecordId)
            .orElseThrow { CustomException(ErrorCode.GRADING_RECORD_NOT_FOUND) }
    }

    fun findAllCompletedByStudentIdAndDate(studentId: Long, date: LocalDate): List<GradingRecord> {
        val start = date.atStartOfDay()
        val end = start.plusDays(1)
        return gradingRecordRepository.findAllByStudentIdAndStatusAndCreatedAtBetweenOrderByCreatedAtDesc(
            studentId, GradingStatus.COMPLETED, start, end
        )
    }

    fun findAllCompleted(): List<GradingRecord> {
        return gradingRecordRepository.findAllByStatusWithStudentAndWorksheet(GradingStatus.COMPLETED)
    }
}
