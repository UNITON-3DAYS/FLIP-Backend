package com.flip.domain.grading.presentation

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

data class EndGradingSessionRequest(
    @field:NotNull
    @field:Schema(description = "채점할 이미지 ID", example = "1")
    val gradingImageId: Long
)
