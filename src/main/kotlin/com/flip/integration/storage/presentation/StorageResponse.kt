package com.flip.integration.storage.presentation

import io.swagger.v3.oas.annotations.media.Schema

data class StorageResponse(
    @field:Schema(description = "업로드된 파일 URL", example = "https://example.com/photo.jpg")
    val fileUrl: String
)
