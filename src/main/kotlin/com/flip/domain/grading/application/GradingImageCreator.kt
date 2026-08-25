package com.flip.domain.grading.application

import com.flip.domain.grading.domain.GradingImage
import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.infrastructure.GradingImageRepository
import org.springframework.stereotype.Component

@Component
class GradingImageCreator(
    private val gradingImageRepository: GradingImageRepository
) {
    fun create(gradingRecord: GradingRecord, imageUrl: String): GradingImage {
        return gradingImageRepository.save(GradingImage(gradingRecord = gradingRecord, imageUrl = imageUrl))
    }
}
