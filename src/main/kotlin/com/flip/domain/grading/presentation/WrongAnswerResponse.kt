package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingResult
import io.swagger.v3.oas.annotations.media.Schema

data class WrongAnswerResponse(
    @field:Schema(description = "문항 번호", example = "3")
    val questionNumber: String,

    @field:Schema(description = "학생이 작성한 답안", example = "12")
    val studentAnswer: String
) {
    companion object {
        fun from(gradingResult: GradingResult) = WrongAnswerResponse(
            questionNumber = gradingResult.questionNumber,
            studentAnswer = gradingResult.studentAnswer
        )
    }
}
