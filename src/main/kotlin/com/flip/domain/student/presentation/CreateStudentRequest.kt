package com.flip.domain.student.presentation

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateStudentRequest(
    @field:NotBlank
    @field:Schema(description = "학생 이름", example = "홍길동")
    val name: String,

    @field:NotNull
    @field:Schema(description = "학년", example = "1")
    val grade: Int,

    @field:NotBlank
    @field:Schema(description = "비밀번호", example = "password1234")
    val password: String,

    @field:NotNull
    @field:Schema(description = "학교 ID", example = "1")
    val schoolId: Long
)
