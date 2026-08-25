package com.flip.domain.grading.application

import com.flip.domain.grading.presentation.AdminGradingRecordListResponse
import com.flip.domain.grading.presentation.CreateGradingSessionRequest
import com.flip.domain.grading.presentation.EndGradingSessionRequest
import com.flip.domain.grading.presentation.GradingImageResponse
import com.flip.domain.grading.presentation.GradingRecordDetailResponse
import com.flip.domain.grading.presentation.GradingRecordListResponse
import com.flip.domain.grading.presentation.GradingRecordStatusResponse
import com.flip.domain.grading.presentation.GradingSessionResponse
import com.flip.domain.grading.presentation.UploadGradingImageRequest
import com.flip.domain.student.application.StudentReader
import com.flip.domain.worksheet.application.WorksheetCreator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.DateTimeException
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class GradingRecordService(
    private val studentReader: StudentReader,
    private val worksheetCreator: WorksheetCreator,
    private val gradingRecordCreator: GradingRecordCreator,
    private val gradingRecordReader: GradingRecordReader,
    private val gradingRecordValidator: GradingRecordValidator,
    private val gradingImageCreator: GradingImageCreator,
    private val gradingImageReader: GradingImageReader,
    private val gradingResultRecorder: GradingResultRecorder,
    private val gradingResultReader: GradingResultReader
) {
    @Transactional
    fun createSession(studentId: Long, request: CreateGradingSessionRequest): GradingSessionResponse {
        val student = studentReader.getById(studentId)
        val worksheet = worksheetCreator.findOrCreate(request.worksheetTitle, request.worksheetSource)
        val gradingRecord = gradingRecordCreator.create(student, worksheet)
        return GradingSessionResponse.from(gradingRecord)
    }

    @Transactional
    fun uploadImage(studentId: Long, gradingRecordId: Long, request: UploadGradingImageRequest): GradingImageResponse {
        val gradingRecord = gradingRecordReader.getById(gradingRecordId)
        gradingRecordValidator.validateOwner(gradingRecord, studentId)
        gradingRecordValidator.validateInProgress(gradingRecord)
        val gradingImage = gradingImageCreator.create(gradingRecord, request.imageUrl)
        gradingResultRecorder.record(gradingRecord, gradingRecord.worksheet, request.imageUrl)
        return GradingImageResponse.from(gradingImage)
    }

    @Transactional
    fun endSession(studentId: Long, gradingRecordId: Long, request: EndGradingSessionRequest): GradingSessionResponse {
        val gradingRecord = gradingRecordReader.getById(gradingRecordId)
        gradingRecordValidator.validateOwner(gradingRecord, studentId)
        gradingRecordValidator.validateInProgress(gradingRecord)
        gradingImageReader.validateBelongsTo(request.gradingImageId, gradingRecordId)
        gradingRecord.complete()
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
        val gradingResults = gradingResultReader.findAllByGradingRecordId(gradingRecordId)
        return GradingRecordDetailResponse.of(gradingRecord, gradingResults)
    }

    fun getList(studentId: Long, year: Int, month: Int, day: Int): GradingRecordListResponse {
        val date = toLocalDate(year, month, day)
        val gradingRecords = gradingRecordReader.findAllCompletedByStudentIdAndDate(studentId, date)
        return GradingRecordListResponse.from(gradingRecords)
    }

    fun getAdminList(): AdminGradingRecordListResponse {
        val gradingRecords = gradingRecordReader.findAllCompleted()
        return AdminGradingRecordListResponse.from(gradingRecords)
    }

    fun getAdminDetail(gradingRecordId: Long): GradingRecordDetailResponse {
        val gradingRecord = gradingRecordReader.getById(gradingRecordId)
        val gradingResults = gradingResultReader.findAllByGradingRecordId(gradingRecordId)
        return GradingRecordDetailResponse.of(gradingRecord, gradingResults)
    }

    private fun toLocalDate(year: Int, month: Int, day: Int): LocalDate {
        try {
            return LocalDate.of(year, month, day)
        } catch (e: DateTimeException) {
            throw IllegalArgumentException("유효하지 않은 날짜입니다: $year-$month-$day", e)
        }
    }
}
