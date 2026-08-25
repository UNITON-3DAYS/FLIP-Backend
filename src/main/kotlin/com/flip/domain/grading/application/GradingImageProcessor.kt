package com.flip.domain.grading.application

import com.flip.domain.grading.domain.GradingImage
import com.flip.domain.grading.domain.GradingRecord
import com.flip.integration.ocr.application.GradingOcrClient
import com.flip.integration.storage.application.FileUploader
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.Base64
import java.util.concurrent.CompletableFuture

@Component
class GradingImageProcessor(
    private val fileUploader: FileUploader,
    private val gradingOcrClient: GradingOcrClient,
    private val gradingImageCreator: GradingImageCreator,
    private val gradingResultRecorder: GradingResultRecorder
) {
    private val log = LoggerFactory.getLogger(GradingImageProcessor::class.java)

    fun process(gradingRecord: GradingRecord, file: MultipartFile): GradingImage {
        val base64 = Base64.getEncoder().encodeToString(file.bytes)
        val worksheet = gradingRecord.worksheet

        val start = System.currentTimeMillis()

        val storageFuture = CompletableFuture.supplyAsync {
            val t0 = System.currentTimeMillis()
            fileUploader.upload(file).also {
                log.info("[GradingImageProcessor] GCS upload took {}ms", System.currentTimeMillis() - t0)
            }
        }
        val ocrFuture = CompletableFuture.supplyAsync {
            val t0 = System.currentTimeMillis()
            gradingOcrClient.grade(worksheet.source, worksheet.title, base64).also {
                log.info("[GradingImageProcessor] AI OCR call took {}ms", System.currentTimeMillis() - t0)
            }
        }

        val storageResponse = storageFuture.join()
        val ocrResult = ocrFuture.join()

        val elapsed = System.currentTimeMillis() - start
        log.info("[GradingImageProcessor] parallel wait took {}ms (vs sum if sequential)", elapsed)

        val gradingImage = gradingImageCreator.create(gradingRecord, storageResponse.fileUrl)
        gradingResultRecorder.record(gradingRecord, ocrResult)
        return gradingImage
    }
}
