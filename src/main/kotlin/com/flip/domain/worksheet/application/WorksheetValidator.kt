package com.flip.domain.worksheet.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.grading.infrastructure.GradingRecordRepository
import com.flip.domain.worksheet.domain.WorksheetSource
import com.flip.domain.worksheet.infrastructure.QuestionRepository
import com.flip.domain.worksheet.infrastructure.WorksheetRepository
import org.springframework.stereotype.Component

@Component
class WorksheetValidator(
    private val worksheetRepository: WorksheetRepository,
    private val gradingRecordRepository: GradingRecordRepository,
    private val questionRepository: QuestionRepository
) {
    fun validateNotDuplicated(title: String, source: WorksheetSource) {
        if (worksheetRepository.findByTitleAndSource(title, source) != null) {
            throw CustomException(ErrorCode.WORKSHEET_ALREADY_EXISTS)
        }
    }

    fun validateNotDuplicatedForUpdate(worksheetId: Long, title: String, source: WorksheetSource) {
        val existing = worksheetRepository.findByTitleAndSource(title, source)
        if (existing != null && existing.id != worksheetId) {
            throw CustomException(ErrorCode.WORKSHEET_ALREADY_EXISTS)
        }
    }

    fun validateNoReferences(worksheetId: Long) {
        if (gradingRecordRepository.existsByWorksheetId(worksheetId) ||
            questionRepository.existsByWorksheetId(worksheetId)
        ) {
            throw CustomException(ErrorCode.WORKSHEET_HAS_REFERENCES)
        }
    }
}
