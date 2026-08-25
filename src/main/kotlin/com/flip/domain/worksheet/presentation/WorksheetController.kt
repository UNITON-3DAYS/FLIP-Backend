package com.flip.domain.worksheet.presentation

import com.flip.domain.worksheet.application.WorksheetService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/worksheets")
@Tag(name = "Worksheet", description = "문제지 관련 API입니다.")
class WorksheetController(
    private val worksheetService: WorksheetService
) {
    @GetMapping
    @Operation(summary = "문제지 목록 조회", description = "전체 문제지 목록을 조회합니다.")
    fun getList(): ResponseEntity<WorksheetListResponse> {
        val response = worksheetService.getList()
        return ResponseEntity(response, HttpStatus.OK)
    }
}
