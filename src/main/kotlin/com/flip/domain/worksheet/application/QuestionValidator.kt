package com.flip.domain.worksheet.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.worksheet.domain.Question
import com.flip.domain.worksheet.infrastructure.QuestionRepository
import org.springframework.stereotype.Component

@Component
class QuestionValidator(
    private val questionRepository: QuestionRepository
) {
    fun validateBelongsToWorksheet(question: Question, worksheetId: Long) {
        if (question.worksheet.id != worksheetId) {
            throw CustomException(ErrorCode.QUESTION_NOT_FOUND)
        }
    }

    fun validateNotDuplicated(worksheetId: Long, page: Int, questionNumber: Int) {
        if (questionRepository.existsByWorksheetIdAndPageAndQuestionNumber(worksheetId, page, questionNumber)) {
            throw CustomException(ErrorCode.QUESTION_ALREADY_EXISTS)
        }
    }
}
