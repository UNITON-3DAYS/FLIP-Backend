package com.flip.domain.grading.infrastructure

import com.flip.domain.grading.domain.GradingResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GradingResultRepository : JpaRepository<GradingResult, Long> {
    fun findAllByGradingRecordId(gradingRecordId: Long): List<GradingResult>

    fun deleteAllByGradingRecordIdIn(gradingRecordIds: List<Long>)

    fun findByGradingRecordIdAndPageAndQuestionNumber(
        gradingRecordId: Long,
        page: String,
        questionNumber: String
    ): GradingResult?
}
