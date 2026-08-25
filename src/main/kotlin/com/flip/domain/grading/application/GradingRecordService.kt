package com.flip.domain.grading.application

import com.flip.domain.grading.presentation.CreateGradingSessionRequest
import com.flip.domain.grading.presentation.GradingSessionResponse
import com.flip.domain.student.application.StudentReader
import com.flip.domain.worksheet.application.WorksheetCreator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GradingRecordService(
    private val studentReader: StudentReader,
    private val worksheetCreator: WorksheetCreator,
    private val gradingRecordCreator: GradingRecordCreator
) {
    @Transactional
    fun createSession(studentId: Long, request: CreateGradingSessionRequest): GradingSessionResponse {
        val student = studentReader.getById(studentId)
        val worksheet = worksheetCreator.findOrCreate(request.worksheetTitle, request.worksheetSource)
        val gradingRecord = gradingRecordCreator.create(student, worksheet)
        return GradingSessionResponse.from(gradingRecord)
    }
}
