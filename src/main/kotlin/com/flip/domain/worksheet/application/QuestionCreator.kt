package com.flip.domain.worksheet.application

import com.flip.domain.worksheet.domain.Question
import com.flip.domain.worksheet.domain.QuestionType
import com.flip.domain.worksheet.domain.Worksheet
import com.flip.domain.worksheet.infrastructure.QuestionRepository
import org.springframework.stereotype.Component

@Component
class QuestionCreator(
    private val questionRepository: QuestionRepository
) {
    fun create(
        worksheet: Worksheet,
        questionNumber: Int,
        page: Int,
        correctAnswer: String,
        type: QuestionType?,
        numChoices: Int?
    ): Question {
        return questionRepository.save(
            Question(
                worksheet = worksheet,
                questionNumber = questionNumber,
                page = page,
                correctAnswer = correctAnswer,
                type = type,
                numChoices = numChoices
            )
        )
    }
}
