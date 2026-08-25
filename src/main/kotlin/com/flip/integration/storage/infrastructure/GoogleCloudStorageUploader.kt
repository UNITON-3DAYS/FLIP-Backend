package com.flip.integration.storage.infrastructure

import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import com.flip.integration.storage.application.FileUploader
import com.flip.integration.storage.presentation.StorageResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.nio.channels.Channels
import java.util.Base64
import java.util.UUID

@Component
class GoogleCloudStorageUploader(
    private val storage: Storage,
    @Value("\${gcp.storage.bucket}")
    private val bucketName: String
) : FileUploader {

    override fun upload(file: MultipartFile): StorageResponse {
        val uuid = UUID.randomUUID().toString()
        val blobInfo = BlobInfo.newBuilder(bucketName, uuid)
            .setContentType(file.contentType)
            .build()

        storage.writer(blobInfo).use { writeChannel ->
            Channels.newOutputStream(writeChannel).use { outputStream ->
                file.inputStream.copyTo(outputStream)
            }
        }

        return StorageResponse(generateFileUrl(uuid))
    }

    override fun downloadAsBase64(fileUrl: String): String {
        val objectName = fileUrl.substringAfterLast("$bucketName/")
        val bytes = storage.get(BlobId.of(bucketName, objectName))?.getContent()
            ?: throw IllegalStateException("이미지를 찾을 수 없습니다: $fileUrl")
        return Base64.getEncoder().encodeToString(bytes)
    }

    private fun generateFileUrl(fileName: String): String {
        return "https://storage.googleapis.com/$bucketName/$fileName"
    }
}
