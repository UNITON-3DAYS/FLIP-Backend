package com.flip.domain.grading.infrastructure

import com.flip.domain.grading.domain.GradingImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GradingImageRepository : JpaRepository<GradingImage, Long> {
    fun existsByIdAndGradingRecordId(id: Long, gradingRecordId: Long): Boolean

    @Modifying
    @Query("DELETE FROM GradingImage gi WHERE gi.gradingRecord.id IN :gradingRecordIds")
    fun deleteAllByGradingRecordIdIn(@Param("gradingRecordIds") gradingRecordIds: List<Long>)
}
