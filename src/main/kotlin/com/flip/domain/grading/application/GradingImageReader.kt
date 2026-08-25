package com.flip.domain.grading.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.grading.infrastructure.GradingImageRepository
import org.springframework.stereotype.Component

@Component
class GradingImageReader(
    private val gradingImageRepository: GradingImageRepository
) {
    fun validateBelongsTo(gradingImageId: Long, gradingRecordId: Long) {
        if (!gradingImageRepository.existsByIdAndGradingRecordId(gradingImageId, gradingRecordId)) {
            throw CustomException(ErrorCode.GRADING_IMAGE_NOT_FOUND)
        }
    }
}
