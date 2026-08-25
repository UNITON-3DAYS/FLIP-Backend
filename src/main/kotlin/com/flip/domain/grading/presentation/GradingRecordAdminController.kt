package com.flip.domain.grading.presentation

import com.flip.domain.grading.application.GradingRecordService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/grading-records")
@Tag(name = "GradingRecordAdmin", description = "관리자용 채점 기록 조회 API입니다.")
class GradingRecordAdminController(
    private val gradingRecordService: GradingRecordService
) {
    @GetMapping
    @Operation(summary = "채점 내역 목록 조회", description = "전체 학생의 완료된 채점 기록 목록을 조회합니다.")
    fun getList(): ResponseEntity<AdminGradingRecordListResponse> {
        val response = gradingRecordService.getAdminList()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{gradingRecordId}")
    @Operation(summary = "상세 채점 결과 조회")
    fun getDetail(@PathVariable gradingRecordId: Long): ResponseEntity<GradingRecordDetailResponse> {
        val response = gradingRecordService.getAdminDetail(gradingRecordId)
        return ResponseEntity(response, HttpStatus.OK)
    }
}
