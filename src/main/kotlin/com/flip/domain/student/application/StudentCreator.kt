package com.flip.domain.student.application

import com.flip.domain.school.domain.School
import com.flip.domain.student.domain.Student
import com.flip.domain.student.infrastructure.StudentRepository
import org.springframework.stereotype.Component

@Component
class StudentCreator(
    private val studentRepository: StudentRepository
) {
    fun create(school: School, grade: Int, name: String, password: String): Student {
        return studentRepository.save(Student(school = school, grade = grade, name = name, password = password))
    }
}
