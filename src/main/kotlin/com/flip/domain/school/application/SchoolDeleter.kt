package com.flip.domain.school.application

import com.flip.domain.school.domain.School
import com.flip.domain.school.infrastructure.SchoolRepository
import org.springframework.stereotype.Component

@Component
class SchoolDeleter(
    private val schoolRepository: SchoolRepository
) {
    fun delete(school: School) {
        schoolRepository.delete(school)
    }
}
