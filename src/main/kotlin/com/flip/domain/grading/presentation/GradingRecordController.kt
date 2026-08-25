package com.flip.domain.grading.presentation

import com.flip.domain.grading.application.GradingRecordService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    @PostMapping
    fun createSession(
        @RequestHeader("studentId") studentId: Long,
        @RequestBody @Valid request: CreateGradingSessionRequest
    ): ResponseEntity<GradingSessionResponse> {
        val response = gradingRecordService.createSession(studentId, request)
        return ResponseEntity(response, HttpStatus.OK)
    }
}
