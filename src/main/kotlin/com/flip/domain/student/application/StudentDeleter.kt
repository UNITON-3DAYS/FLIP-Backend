package com.flip.domain.student.application

import com.flip.domain.student.domain.Student
import com.flip.domain.student.infrastructure.StudentRepository
import org.springframework.stereotype.Component

@Component
class StudentDeleter(
    private val studentRepository: StudentRepository
) {
    fun delete(student: Student) {
        studentRepository.delete(student)
    }
}
