package com.flip.domain.worksheet.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.worksheet.domain.Question
import com.flip.domain.worksheet.infrastructure.QuestionRepository
import org.springframework.stereotype.Component

@Component
class QuestionReader(
    private val questionRepository: QuestionRepository
) {
    fun getById(questionId: Long): Question {
        return questionRepository.findById(questionId)
            .orElseThrow { CustomException(ErrorCode.QUESTION_NOT_FOUND) }
    }

    fun findAllByWorksheetId(worksheetId: Long): List<Question> {
        return questionRepository.findAllByWorksheetIdOrderByPageAscQuestionNumberAsc(worksheetId)
    }
}
