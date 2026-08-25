package com.flip.domain.student.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.student.domain.Student
import com.flip.domain.student.infrastructure.StudentRepository
import org.springframework.stereotype.Component

@Component
class StudentReader(
    private val studentRepository: StudentRepository
) {
    fun getById(studentId: Long): Student {
        return studentRepository.findById(studentId)
            .orElseThrow { CustomException(ErrorCode.STUDENT_NOT_FOUND) }
    }
}
