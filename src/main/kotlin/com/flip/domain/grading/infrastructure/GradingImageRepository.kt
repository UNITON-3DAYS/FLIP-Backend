package com.flip.domain.grading.infrastructure

import com.flip.domain.grading.domain.GradingImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GradingImageRepository : JpaRepository<GradingImage, Long> {
    fun existsByIdAndGradingRecordId(id: Long, gradingRecordId: Long): Boolean
}
