package com.flip.domain.grading.infrastructure

import com.flip.domain.grading.domain.WrongAnswer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface WrongAnswerRepository : JpaRepository<WrongAnswer, Long> {

    @Query("SELECT w FROM WrongAnswer w JOIN FETCH w.question WHERE w.gradingRecord.id = :gradingRecordId")
    fun findAllWithQuestionByGradingRecordId(@Param("gradingRecordId") gradingRecordId: Long): List<WrongAnswer>
}
