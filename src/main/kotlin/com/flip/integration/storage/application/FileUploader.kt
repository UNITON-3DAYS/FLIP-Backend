package com.flip.integration.storage.application

import com.flip.integration.storage.presentation.StorageResponse
import org.springframework.web.multipart.MultipartFile

interface FileUploader {
    fun upload(file: MultipartFile): StorageResponse
    fun downloadAsBase64(fileUrl: String): String
}
