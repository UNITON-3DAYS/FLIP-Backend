package com.flip.domain.grading.presentation

import com.flip.domain.worksheet.domain.WorksheetSource
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateGradingSessionRequest(
    @field:NotNull
    val worksheetSource: WorksheetSource,

    @field:NotBlank
    val worksheetTitle: String
)
