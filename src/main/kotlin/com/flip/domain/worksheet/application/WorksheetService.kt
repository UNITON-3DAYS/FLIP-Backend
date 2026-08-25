package com.flip.domain.worksheet.application

import com.flip.domain.worksheet.infrastructure.WorksheetRepository
import com.flip.domain.worksheet.presentation.CreateWorksheetRequest
import com.flip.domain.worksheet.presentation.UpdateWorksheetRequest
import com.flip.domain.worksheet.presentation.WorksheetListResponse
import com.flip.domain.worksheet.presentation.WorksheetResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WorksheetService(
    private val worksheetReader: WorksheetReader,
    private val worksheetCreator: WorksheetCreator,
    private val worksheetValidator: WorksheetValidator,
    private val worksheetRepository: WorksheetRepository
) {
    fun getList(): WorksheetListResponse {
        val worksheets = worksheetReader.findAll()
        return WorksheetListResponse.from(worksheets)
    }

    @Transactional
    fun create(request: CreateWorksheetRequest): WorksheetResponse {
        worksheetValidator.validateNotDuplicated(request.title, request.source)
        val worksheet = worksheetCreator.create(request.title, request.source)
        return WorksheetResponse.from(worksheet)
    }

    @Transactional
    fun update(worksheetId: Long, request: UpdateWorksheetRequest): WorksheetResponse {
        val worksheet = worksheetReader.getById(worksheetId)
        worksheetValidator.validateNotDuplicatedForUpdate(worksheetId, request.title, request.source)
        worksheet.title = request.title
        worksheet.source = request.source
        return WorksheetResponse.from(worksheet)
    }

    @Transactional
    fun delete(worksheetId: Long) {
        val worksheet = worksheetReader.getById(worksheetId)
        worksheetValidator.validateNoReferences(worksheetId)
        worksheetRepository.delete(worksheet)
    }
}
