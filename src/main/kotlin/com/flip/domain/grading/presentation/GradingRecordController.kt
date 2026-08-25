package com.flip.domain.grading.presentation

import com.flip.domain.grading.application.GradingRecordService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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
@RequestMapping("/api/grading-records")
@Tag(name = "GradingRecord", description = "채점 기록 관련 API입니다.")
class GradingRecordController(
    private val gradingRecordService: GradingRecordService
) {
    @GetMapping
    @Operation(summary = "채점 기록 목록 조회")
    fun getList(
        @RequestHeader("studentId") studentId: Long
    ): ResponseEntity<GradingRecordListResponse> {
        val response = gradingRecordService.getList(studentId)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    @Operation(summary = "채점 세션 생성", description = "새로운 채점 세션을 시작합니다.")
    fun createSession(
        @RequestHeader("studentId") studentId: Long,
        @RequestBody @Valid request: CreateGradingSessionRequest
    ): ResponseEntity<GradingSessionResponse> {
        val response = gradingRecordService.createSession(studentId, request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/{gradingRecordId}/images")
    @Operation(summary = "채점 이미지 업로드", description = "채점 세션에 채점할 이미지를 등록합니다.")
    fun uploadImage(
        @RequestHeader("studentId") studentId: Long,
        @PathVariable gradingRecordId: Long,
        @RequestBody @Valid request: UploadGradingImageRequest
    ): ResponseEntity<GradingImageResponse> {
        val response = gradingRecordService.uploadImage(studentId, gradingRecordId, request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PatchMapping("/{gradingRecordId}")
    @Operation(summary = "채점 세션 종료", description = "채점 세션을 종료하고 채점을 진행합니다.")
    fun endSession(
        @RequestHeader("studentId") studentId: Long,
        @PathVariable gradingRecordId: Long,
        @RequestBody @Valid request: EndGradingSessionRequest
    ): ResponseEntity<GradingSessionResponse> {
        val response = gradingRecordService.endSession(studentId, gradingRecordId, request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{gradingRecordId}/status")
    @Operation(summary = "채점 상태 조회")
    fun getStatus(
        @RequestHeader("studentId") studentId: Long,
        @PathVariable gradingRecordId: Long
    ): ResponseEntity<GradingRecordStatusResponse> {
        val response = gradingRecordService.getStatus(studentId, gradingRecordId)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{gradingRecordId}")
    @Operation(summary = "채점 기록 상세 조회")
    fun getDetail(
        @RequestHeader("studentId") studentId: Long,
        @PathVariable gradingRecordId: Long
    ): ResponseEntity<GradingRecordDetailResponse> {
        val response = gradingRecordService.getDetail(studentId, gradingRecordId)
        return ResponseEntity(response, HttpStatus.OK)
    }
}
