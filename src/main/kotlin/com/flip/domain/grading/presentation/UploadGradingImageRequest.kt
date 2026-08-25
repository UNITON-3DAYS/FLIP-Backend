package com.flip.domain.grading.presentation

import jakarta.validation.constraints.NotBlank

data class UploadGradingImageRequest(
    @field:NotBlank
    val imageUrl: String
)
