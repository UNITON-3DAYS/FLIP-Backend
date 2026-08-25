package com.flip.domain.worksheet.presentation

import com.flip.domain.worksheet.application.WorksheetService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @PostMapping
    @Operation(summary = "문제지 생성", description = "새로운 문제지를 생성합니다.")
    fun create(
        @RequestBody @Valid request: CreateWorksheetRequest
    ): ResponseEntity<WorksheetResponse> {
        val response = worksheetService.create(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PatchMapping("/{worksheetId}")
    @Operation(summary = "문제지 수정", description = "문제지의 타이틀과 유형을 수정합니다.")
    fun update(
        @PathVariable worksheetId: Long,
        @RequestBody @Valid request: UpdateWorksheetRequest
    ): ResponseEntity<WorksheetResponse> {
        val response = worksheetService.update(worksheetId, request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/{worksheetId}")
    @Operation(summary = "문제지 삭제", description = "문제지를 삭제합니다. 채점 기록이나 문항이 있으면 삭제할 수 없습니다.")
    fun delete(
        @PathVariable worksheetId: Long
    ): ResponseEntity<Unit> {
        worksheetService.delete(worksheetId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
