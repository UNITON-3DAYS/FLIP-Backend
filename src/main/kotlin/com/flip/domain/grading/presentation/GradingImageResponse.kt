package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingImage
import io.swagger.v3.oas.annotations.media.Schema

data class GradingImageResponse(
    @field:Schema(description = "생성된 채점 이미지 ID", example = "1")
    val gradingImageId: Long
) {
    companion object {
        fun from(gradingImage: GradingImage) = GradingImageResponse(
            gradingImageId = gradingImage.id!!
        )
    }
}
