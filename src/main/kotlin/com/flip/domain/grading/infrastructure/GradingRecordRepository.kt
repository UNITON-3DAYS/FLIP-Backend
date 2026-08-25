package com.flip.domain.grading.infrastructure

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface GradingRecordRepository : JpaRepository<GradingRecord, Long> {
    fun existsByStudentId(studentId: Long): Boolean

    fun existsByWorksheetId(worksheetId: Long): Boolean

    @Query("SELECT gr.id FROM GradingRecord gr WHERE gr.student.id = :studentId")
    fun findIdsByStudentId(@Param("studentId") studentId: Long): List<Long>

    @Modifying
    @Query("DELETE FROM GradingRecord gr WHERE gr.student.id = :studentId")
    fun deleteAllByStudentId(@Param("studentId") studentId: Long)

    fun findAllByStudentIdAndStatusAndCreatedAtBetweenOrderByCreatedAtDesc(
        studentId: Long,
        status: GradingStatus,
        start: LocalDateTime,
        end: LocalDateTime
    ): List<GradingRecord>

    @Query(
        "SELECT gr FROM GradingRecord gr JOIN FETCH gr.student JOIN FETCH gr.worksheet " +
            "WHERE gr.status = :status ORDER BY gr.createdAt DESC"
    )
    fun findAllByStatusWithStudentAndWorksheet(@Param("status") status: GradingStatus): List<GradingRecord>
}
