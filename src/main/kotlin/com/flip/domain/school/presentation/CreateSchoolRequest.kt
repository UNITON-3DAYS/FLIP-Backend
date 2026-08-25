package com.flip.domain.school.presentation

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class CreateSchoolRequest(
    @field:NotBlank
    @field:Schema(description = "학교 이름", example = "서울고등학교")
    val name: String
)
