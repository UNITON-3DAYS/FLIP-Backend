package com.flip.domain.school.presentation

import com.flip.domain.school.application.SchoolService
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
@RequestMapping("/api/schools")
@Tag(name = "School", description = "학교 관련 API입니다.")
class SchoolController(
    private val schoolService: SchoolService
) {
    @GetMapping
    @Operation(summary = "학교 목록 조회", description = "전체 학교 목록을 조회합니다.")
    fun getList(): ResponseEntity<SchoolListResponse> {
        val response = schoolService.getList()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    @Operation(summary = "학교 생성", description = "새로운 학교를 생성합니다.")
    fun create(
        @RequestBody @Valid request: CreateSchoolRequest
    ): ResponseEntity<SchoolResponse> {
        val response = schoolService.create(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PatchMapping("/{schoolId}")
    @Operation(summary = "학교 수정", description = "학교 이름을 수정합니다.")
    fun update(
        @PathVariable schoolId: Long,
        @RequestBody @Valid request: UpdateSchoolRequest
    ): ResponseEntity<SchoolResponse> {
        val response = schoolService.update(schoolId, request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/{schoolId}")
    @Operation(summary = "학교 삭제", description = "학교를 삭제합니다. 소속 학생이 있으면 삭제할 수 없습니다.")
    fun delete(
        @PathVariable schoolId: Long
    ): ResponseEntity<Unit> {
        schoolService.delete(schoolId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
