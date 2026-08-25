package com.flip.domain.grading.presentation

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class UploadGradingImageRequest(
    @field:NotBlank
    @field:Schema(description = "채점할 이미지 URL", example = "https://example.com/photo.jpg")
    val imageUrl: String
)
