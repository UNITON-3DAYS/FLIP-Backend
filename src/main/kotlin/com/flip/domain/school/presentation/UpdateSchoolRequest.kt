package com.flip.domain.school.presentation

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class UpdateSchoolRequest(
    @field:NotBlank
    @field:Schema(description = "학교 이름", example = "부산고등학교")
    val name: String
)
