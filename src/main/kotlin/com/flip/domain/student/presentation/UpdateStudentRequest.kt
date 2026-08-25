package com.flip.domain.student.presentation

import io.swagger.v3.oas.annotations.media.Schema

data class UpdateStudentRequest(
    @field:Schema(description = "학생 이름", example = "홍길동")
    val name: String? = null,

    @field:Schema(description = "학년", example = "2")
    val grade: Int? = null,

    @field:Schema(description = "학교 ID", example = "2")
    val schoolId: Long? = null,

    @field:Schema(description = "비밀번호", example = "newpassword1234")
    val password: String? = null
)
