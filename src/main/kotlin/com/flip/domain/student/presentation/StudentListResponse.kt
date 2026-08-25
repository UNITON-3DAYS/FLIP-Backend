package com.flip.domain.student.presentation

import com.flip.domain.student.domain.Student
import io.swagger.v3.oas.annotations.media.Schema

data class StudentListResponse(
    @field:Schema(description = "학생 목록")
    val students: List<StudentResponse>
) {
    companion object {
        fun from(students: List<Student>) = StudentListResponse(
            students = students.map { StudentResponse.from(it) }
        )
    }
}

data class StudentResponse(
    @field:Schema(description = "학생 ID", example = "1")
    val studentId: Long,

    @field:Schema(description = "학년", example = "1")
    val grade: Int,

    @field:Schema(description = "학생 이름", example = "홍길동")
    val name: String
) {
    companion object {
        fun from(student: Student) = StudentResponse(
            studentId = student.id!!,
            grade = student.grade,
            name = student.name
        )
    }
}
