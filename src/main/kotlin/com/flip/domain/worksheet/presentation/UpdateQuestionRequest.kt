package com.flip.domain.worksheet.presentation

import com.flip.domain.worksheet.domain.QuestionType
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class UpdateQuestionRequest(
    @field:Positive
    @field:Schema(description = "문항 번호", example = "1")
    val questionNumber: Int,

    @field:Positive
    @field:Schema(description = "페이지 번호", example = "1")
    val page: Int,

    @field:NotBlank
    @field:Schema(description = "정답", example = "3")
    val correctAnswer: String,

    @field:Schema(description = "문항 유형 (MULTIPLE_CHOICE 또는 SUBJECTIVE)", example = "MULTIPLE_CHOICE")
    val type: QuestionType? = null,

    @field:Positive
    @field:Schema(description = "객관식 선지 수 (주관식은 불필요)", example = "5")
    val numChoices: Int? = null
)
