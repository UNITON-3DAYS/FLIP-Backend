package com.flip.domain.school.application

import com.flip.domain.school.domain.School
import com.flip.domain.school.infrastructure.SchoolRepository
import org.springframework.stereotype.Component

@Component
class SchoolCreator(
    private val schoolRepository: SchoolRepository
) {
    fun create(name: String): School {
        return schoolRepository.save(School(name = name))
    }
}
