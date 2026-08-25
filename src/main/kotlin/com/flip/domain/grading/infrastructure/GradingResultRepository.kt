package com.flip.domain.grading.infrastructure

import com.flip.domain.grading.domain.GradingResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GradingResultRepository : JpaRepository<GradingResult, Long> {
    fun findAllByGradingRecordId(gradingRecordId: Long): List<GradingResult>

    @Modifying
    @Query("DELETE FROM GradingResult gr WHERE gr.gradingRecord.id IN :gradingRecordIds")
    fun deleteAllByGradingRecordIdIn(@Param("gradingRecordIds") gradingRecordIds: List<Long>)

    fun findByGradingRecordIdAndPageAndQuestionNumber(
        gradingRecordId: Long,
        page: String,
        questionNumber: String
    ): GradingResult?
}
