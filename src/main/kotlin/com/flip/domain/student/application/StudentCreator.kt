package com.flip.domain.student.application

import com.flip.domain.school.domain.School
import com.flip.domain.student.domain.Student
import com.flip.domain.student.infrastructure.StudentRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class StudentCreator(
    private val studentRepository: StudentRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {
    fun create(name: String, grade: Int, rawPassword: String, school: School): Student {
        val student = Student(
            school = school,
            grade = grade,
            name = name,
            password = passwordEncoder.encode(rawPassword)
        )
        return studentRepository.save(student)
    }
}
