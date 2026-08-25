package com.flip.domain.grading.presentation

import jakarta.validation.constraints.NotNull

data class EndGradingSessionRequest(
    @field:NotNull
    val gradingImageId: Long
)
