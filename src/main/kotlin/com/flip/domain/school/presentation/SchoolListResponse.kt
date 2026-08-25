package com.flip.domain.school.presentation

import com.flip.domain.school.domain.School
import io.swagger.v3.oas.annotations.media.Schema

data class SchoolListResponse(
    @field:Schema(description = "학교 목록")
    val schools: List<SchoolResponse>
) {
    companion object {
        fun from(schools: List<School>) = SchoolListResponse(
            schools = schools.map { SchoolResponse.from(it) }
        )
    }
}

data class SchoolResponse(
    @field:Schema(description = "학교 ID", example = "1")
    val schoolId: Long,

    @field:Schema(description = "학교 이름", example = "서울고등학교")
    val name: String
) {
    companion object {
        fun from(school: School) = SchoolResponse(
            schoolId = school.id!!,
            name = school.name
        )
    }
}
