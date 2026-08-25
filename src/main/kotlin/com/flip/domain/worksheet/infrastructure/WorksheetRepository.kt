package com.flip.domain.worksheet.infrastructure

import com.flip.domain.worksheet.domain.Worksheet
import com.flip.domain.worksheet.domain.WorksheetSource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorksheetRepository : JpaRepository<Worksheet, Long> {
    fun findByTitleAndSource(title: String, source: WorksheetSource): Worksheet?
}
