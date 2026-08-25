package com.flip.domain.student.presentation

import com.flip.domain.student.application.StudentService
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

    @GetMapping("/{studentId}")
    @Operation(summary = "학생 상세 조회")
    fun getDetail(@PathVariable studentId: Long): ResponseEntity<StudentDetailResponse> {
        val response = studentService.getDetail(studentId)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    @Operation(summary = "학생 생성", description = "새로운 학생을 생성합니다. 비밀번호는 BCrypt로 해싱됩니다.")
    fun create(
        @RequestBody @Valid request: CreateStudentRequest
    ): ResponseEntity<StudentResponse> {
        val response = studentService.create(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PatchMapping("/{studentId}")
    @Operation(summary = "학생 수정", description = "학생의 이름/학년/학교/비밀번호를 수정합니다.")
    fun update(
        @PathVariable studentId: Long,
        @RequestBody @Valid request: UpdateStudentRequest
    ): ResponseEntity<StudentResponse> {
        val response = studentService.update(studentId, request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/{studentId}")
    @Operation(summary = "학생 삭제", description = "학생을 삭제합니다. 채점 기록이 있으면 삭제할 수 없습니다.")
    fun delete(
        @PathVariable studentId: Long
    ): ResponseEntity<Unit> {
        studentService.delete(studentId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
