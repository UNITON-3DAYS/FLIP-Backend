package com.flip.domain.grading.presentation

import com.flip.domain.grading.application.GradingRecordService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/grading-records")
class GradingRecordController(
    private val gradingRecordService: GradingRecordService
) {
    @GetMapping
    fun getList(
        @RequestHeader("studentId") studentId: Long
    ): ResponseEntity<GradingRecordListResponse> {
        val response = gradingRecordService.getList(studentId)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    fun createSession(
        @RequestHeader("studentId") studentId: Long,
        @RequestBody @Valid request: CreateGradingSessionRequest
    ): ResponseEntity<GradingSessionResponse> {
        val response = gradingRecordService.createSession(studentId, request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/{gradingRecordId}/images")
    fun uploadImage(
        @RequestHeader("studentId") studentId: Long,
        @PathVariable gradingRecordId: Long,
        @RequestBody @Valid request: UploadGradingImageRequest
    ): ResponseEntity<Unit> {
        gradingRecordService.uploadImage(studentId, gradingRecordId, request)
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/{gradingRecordId}")
    fun endSession(
        @RequestHeader("studentId") studentId: Long,
        @PathVariable gradingRecordId: Long
    ): ResponseEntity<GradingSessionResponse> {
        val response = gradingRecordService.endSession(studentId, gradingRecordId)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{gradingRecordId}/status")
    fun getStatus(
        @RequestHeader("studentId") studentId: Long,
        @PathVariable gradingRecordId: Long
    ): ResponseEntity<GradingRecordStatusResponse> {
        val response = gradingRecordService.getStatus(studentId, gradingRecordId)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{gradingRecordId}")
    fun getDetail(
        @RequestHeader("studentId") studentId: Long,
        @PathVariable gradingRecordId: Long
    ): ResponseEntity<GradingRecordDetailResponse> {
        val response = gradingRecordService.getDetail(studentId, gradingRecordId)
        return ResponseEntity(response, HttpStatus.OK)
    }
}
