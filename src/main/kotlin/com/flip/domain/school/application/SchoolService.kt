package com.flip.domain.school.application

import com.flip.domain.school.presentation.CreateSchoolRequest
import com.flip.domain.school.presentation.SchoolListResponse
import com.flip.domain.school.presentation.SchoolResponse
import com.flip.domain.school.presentation.UpdateSchoolRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SchoolService(
    private val schoolReader: SchoolReader,
    private val schoolCreator: SchoolCreator,
    private val schoolValidator: SchoolValidator,
    private val schoolDeleter: SchoolDeleter
) {
    fun getList(): SchoolListResponse {
        val schools = schoolReader.findAll()
        return SchoolListResponse.from(schools)
    }

    @Transactional
    fun create(request: CreateSchoolRequest): SchoolResponse {
        val school = schoolCreator.create(request.name)
        return SchoolResponse.from(school)
    }

    @Transactional
    fun update(schoolId: Long, request: UpdateSchoolRequest): SchoolResponse {
        val school = schoolReader.getById(schoolId)
        school.name = request.name
        return SchoolResponse.from(school)
    }

    @Transactional
    fun delete(schoolId: Long) {
        val school = schoolReader.getById(schoolId)
        schoolValidator.validateDeletable(schoolId)
        schoolDeleter.delete(school)
    }
}
