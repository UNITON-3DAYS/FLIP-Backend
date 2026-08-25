package com.flip.domain.grading.infrastructure

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface GradingRecordRepository : JpaRepository<GradingRecord, Long> {
    fun findAllByStudentIdAndStatusAndCreatedAtBetweenOrderByCreatedAtDesc(
        studentId: Long,
        status: GradingStatus,
        start: LocalDateTime,
        end: LocalDateTime
    ): List<GradingRecord>
}
