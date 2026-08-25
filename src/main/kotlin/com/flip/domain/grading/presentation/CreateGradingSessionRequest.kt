package com.flip.domain.grading.presentation

import com.flip.domain.worksheet.domain.WorksheetSource
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateGradingSessionRequest(
    @field:NotNull
    @field:Schema(description = "학습지 출처 (INHOUSE 또는 EXTERNAL)", example = "INHOUSE")
    val worksheetSource: WorksheetSource,

    @field:NotBlank
    @field:Schema(description = "학습지 제목", example = "중1 수학 1단원 연습문제")
    val worksheetTitle: String
)
