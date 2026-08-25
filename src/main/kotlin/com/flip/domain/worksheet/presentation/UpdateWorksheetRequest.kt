package com.flip.domain.worksheet.presentation

import com.flip.domain.worksheet.domain.WorksheetSource
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateWorksheetRequest(
    @field:NotBlank
    @field:Schema(description = "문제지 타이틀", example = "중1 수학 1단원 연습문제")
    val title: String,

    @field:NotNull
    @field:Schema(description = "문제지 유형 (INHOUSE 또는 EXTERNAL)", example = "INHOUSE")
    val source: WorksheetSource
)
