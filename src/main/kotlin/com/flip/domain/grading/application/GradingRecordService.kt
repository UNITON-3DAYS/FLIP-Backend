package com.flip.domain.grading.application

import com.flip.domain.grading.presentation.CreateGradingSessionRequest
import com.flip.domain.grading.presentation.GradingRecordDetailResponse
import com.flip.domain.grading.presentation.GradingRecordListResponse
import com.flip.domain.grading.presentation.GradingRecordStatusResponse
import com.flip.domain.grading.presentation.GradingSessionResponse
import com.flip.domain.grading.presentation.UploadGradingImageRequest
import com.flip.domain.student.application.StudentReader
import com.flip.domain.worksheet.application.WorksheetCreator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GradingRecordService(
    private val studentReader: StudentReader,
    private val worksheetCreator: WorksheetCreator,
    private val gradingRecordCreator: GradingRecordCreator,
    private val gradingRecordReader: GradingRecordReader,
    private val gradingRecordValidator: GradingRecordValidator,
    private val gradingImageCreator: GradingImageCreator,
    private val wrongAnswerReader: WrongAnswerReader
) {
    @Transactional
    fun createSession(studentId: Long, request: CreateGradingSessionRequest): GradingSessionResponse {
        val student = studentReader.getById(studentId)
        val worksheet = worksheetCreator.findOrCreate(request.worksheetTitle, request.worksheetSource)
        val gradingRecord = gradingRecordCreator.create(student, worksheet)
        return GradingSessionResponse.from(gradingRecord)
    }

    @Transactional
    fun uploadImage(studentId: Long, gradingRecordId: Long, request: UploadGradingImageRequest) {
        val gradingRecord = gradingRecordReader.getById(gradingRecordId)
        gradingRecordValidator.validateOwner(gradingRecord, studentId)
        gradingRecordValidator.validateInProgress(gradingRecord)
        gradingImageCreator.create(gradingRecord, request.imageUrl)
    }

    @Transactional
    fun endSession(studentId: Long, gradingRecordId: Long): GradingSessionResponse {
        val gradingRecord = gradingRecordReader.getById(gradingRecordId)
        gradingRecordValidator.validateOwner(gradingRecord, studentId)
        gradingRecordValidator.validateInProgress(gradingRecord)
        gradingRecord.startGrading()
        return GradingSessionResponse.from(gradingRecord)
    }

    fun getStatus(studentId: Long, gradingRecordId: Long): GradingRecordStatusResponse {
        val gradingRecord = gradingRecordReader.getById(gradingRecordId)
        gradingRecordValidator.validateOwner(gradingRecord, studentId)
        return GradingRecordStatusResponse.from(gradingRecord)
    }

    fun getDetail(studentId: Long, gradingRecordId: Long): GradingRecordDetailResponse {
        val gradingRecord = gradingRecordReader.getById(gradingRecordId)
        gradingRecordValidator.validateOwner(gradingRecord, studentId)
        val wrongAnswers = wrongAnswerReader.findAllByGradingRecordId(gradingRecordId)
        return GradingRecordDetailResponse.of(gradingRecord, wrongAnswers)
    }

    fun getList(studentId: Long): GradingRecordListResponse {
        val gradingRecords = gradingRecordReader.findAllCompletedByStudentId(studentId)
        return GradingRecordListResponse.from(gradingRecords)
    }
}
