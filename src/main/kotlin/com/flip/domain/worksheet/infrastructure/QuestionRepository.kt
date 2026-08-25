package com.flip.domain.worksheet.infrastructure

import com.flip.domain.worksheet.domain.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
    fun existsByWorksheetId(worksheetId: Long): Boolean

    fun findAllByWorksheetIdOrderByPageAscQuestionNumberAsc(worksheetId: Long): List<Question>

    fun existsByWorksheetIdAndPageAndQuestionNumber(worksheetId: Long, page: Int, questionNumber: Int): Boolean
}
