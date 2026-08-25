package com.flip.domain.grading.infrastructure

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GradingRecordRepository : JpaRepository<GradingRecord, Long> {
    fun findAllByStudentIdAndStatusOrderByCreatedAtDesc(studentId: Long, status: GradingStatus): List<GradingRecord>
}
