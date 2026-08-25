package com.flip.domain.school.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.student.infrastructure.StudentRepository
import org.springframework.stereotype.Component

@Component
class SchoolValidator(
    private val studentRepository: StudentRepository
) {
    fun validateDeletable(schoolId: Long) {
        if (studentRepository.existsBySchoolId(schoolId)) {
            throw CustomException(ErrorCode.SCHOOL_HAS_STUDENTS)
        }
    }
}
