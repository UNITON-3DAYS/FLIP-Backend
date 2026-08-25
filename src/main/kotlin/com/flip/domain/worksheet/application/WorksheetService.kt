package com.flip.domain.worksheet.application

import com.flip.domain.worksheet.presentation.WorksheetListResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WorksheetService(
    private val worksheetReader: WorksheetReader
) {
    fun getList(): WorksheetListResponse {
        val worksheets = worksheetReader.findAll()
        return WorksheetListResponse.from(worksheets)
    }
}
