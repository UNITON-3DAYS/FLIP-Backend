package com.flip.domain.school.application

import com.flip.domain.school.presentation.SchoolListResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SchoolService(
    private val schoolReader: SchoolReader
) {
    fun getList(): SchoolListResponse {
        val schools = schoolReader.findAll()
        return SchoolListResponse.from(schools)
    }
}
