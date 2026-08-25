package com.flip.domain.worksheet.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.worksheet.domain.Worksheet
import com.flip.domain.worksheet.infrastructure.WorksheetRepository
import org.springframework.stereotype.Component

@Component
class WorksheetReader(
    private val worksheetRepository: WorksheetRepository
) {
    fun findAll(): List<Worksheet> {
        return worksheetRepository.findAll()
    }

    fun getById(worksheetId: Long): Worksheet {
        return worksheetRepository.findById(worksheetId)
            .orElseThrow { CustomException(ErrorCode.WORKSHEET_NOT_FOUND) }
    }
}
