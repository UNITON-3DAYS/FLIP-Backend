package com.flip.domain.school.application

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
}
