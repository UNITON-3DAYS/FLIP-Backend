package com.flip.domain.grading.application

import com.flip.domain.grading.domain.GradingResult
import com.flip.domain.grading.infrastructure.GradingResultRepository
import org.springframework.stereotype.Component

@Component
class GradingResultReader(
    private val gradingResultRepository: GradingResultRepository
) {
    fun findAllByGradingRecordId(gradingRecordId: Long): List<GradingResult> {
        return gradingResultRepository.findAllByGradingRecordId(gradingRecordId)
    }
}
