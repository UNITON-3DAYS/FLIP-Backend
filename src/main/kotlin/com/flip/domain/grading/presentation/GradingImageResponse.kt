package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingImage

data class GradingImageResponse(
    val gradingImageId: Long
) {
    companion object {
        fun from(gradingImage: GradingImage) = GradingImageResponse(
            gradingImageId = gradingImage.id!!
        )
    }
}
