package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingResult

data class WrongAnswerResponse(
    val questionNumber: String,
    val studentAnswer: String
) {
    companion object {
        fun from(gradingResult: GradingResult) = WrongAnswerResponse(
            questionNumber = gradingResult.questionNumber,
            studentAnswer = gradingResult.studentAnswer
        )
    }
}
