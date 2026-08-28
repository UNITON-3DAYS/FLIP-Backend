package com.flip.domain.worksheet.presentation

import com.flip.domain.worksheet.domain.Question
import com.flip.domain.worksheet.domain.QuestionType
import io.swagger.v3.oas.annotations.media.Schema

data class QuestionListResponse(
    @field:Schema(description = "문항 목록")
    val questions: List<QuestionResponse>
) {
    companion object {
        fun from(questions: List<Question>) = QuestionListResponse(
            questions = questions.map { QuestionResponse.from(it) }
        )
    }
}

data class QuestionResponse(
    @field:Schema(description = "문항 ID", example = "1")
    val questionId: Long,

    @field:Schema(description = "문제지 ID", example = "1")
    val worksheetId: Long,

    @field:Schema(description = "문항 번호", example = "1")
    val questionNumber: Int,

    @field:Schema(description = "페이지 번호", example = "1")
    val page: Int,

    @field:Schema(description = "정답", example = "3")
    val correctAnswer: String,

    @field:Schema(description = "문항 유형", example = "MULTIPLE_CHOICE")
    val type: QuestionType?,

    @field:Schema(description = "객관식 선지 수", example = "5")
    val numChoices: Int?
) {
    companion object {
        fun from(question: Question) = QuestionResponse(
            questionId = question.id!!,
            worksheetId = question.worksheet.id!!,
            questionNumber = question.questionNumber,
            page = question.page,
            correctAnswer = question.correctAnswer,
            type = question.type,
            numChoices = question.numChoices
        )
    }
}
