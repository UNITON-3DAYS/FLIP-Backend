package com.flip.domain.school.presentation

import com.flip.domain.school.application.SchoolService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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
}
