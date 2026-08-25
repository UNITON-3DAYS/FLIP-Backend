package com.flip.domain.school.application

import com.flip.common.exception.CustomException
import com.flip.common.exception.ErrorCode
import com.flip.domain.school.domain.School
import com.flip.domain.school.infrastructure.SchoolRepository
import org.springframework.stereotype.Component

@Component
class SchoolReader(
    private val schoolRepository: SchoolRepository
) {
    fun findAll(): List<School> {
        return schoolRepository.findAll()
    }

    fun getById(schoolId: Long): School {
        return schoolRepository.findById(schoolId)
            .orElseThrow { CustomException(ErrorCode.SCHOOL_NOT_FOUND) }
    }
}
