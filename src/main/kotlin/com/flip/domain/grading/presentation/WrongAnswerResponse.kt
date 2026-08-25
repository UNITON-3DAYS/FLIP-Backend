package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.WrongAnswer

data class WrongAnswerResponse(
    val questionNumber: Int,
    val correctAnswer: String,
    val studentAnswer: String
) {
    companion object {
        fun from(wrongAnswer: WrongAnswer) = WrongAnswerResponse(
            questionNumber = wrongAnswer.question.questionNumber,
            correctAnswer = wrongAnswer.question.correctAnswer,
            studentAnswer = wrongAnswer.studentAnswer
        )
    }
}
