package com.flip.domain.worksheet.application

import com.flip.domain.worksheet.domain.Worksheet
import com.flip.domain.worksheet.domain.WorksheetSource
import com.flip.domain.worksheet.infrastructure.WorksheetRepository
import org.springframework.stereotype.Component

@Component
class WorksheetCreator(
    private val worksheetRepository: WorksheetRepository
) {
    fun findOrCreate(title: String, source: WorksheetSource): Worksheet {
        return worksheetRepository.findByTitleAndSource(title, source)
            ?: worksheetRepository.save(Worksheet(title = title, source = source))
    }
}
