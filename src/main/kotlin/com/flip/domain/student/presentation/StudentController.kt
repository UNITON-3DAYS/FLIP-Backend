package com.flip.domain.student.presentation

import com.flip.domain.student.application.StudentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student", description = "학생 관련 API입니다.")
class StudentController(
    private val studentService: StudentService
) {
    @GetMapping
    @Operation(summary = "학생 목록 조회", description = "전체 학생 목록을 조회합니다.")
    fun getList(): ResponseEntity<StudentListResponse> {
        val response = studentService.getList()
        return ResponseEntity(response, HttpStatus.OK)
    }
}
