package com.flip.integration.ocr.infrastructure

import com.flip.domain.worksheet.domain.WorksheetSource
import com.flip.integration.ocr.application.GradingOcrClient
import com.flip.integration.ocr.application.GradingOcrQuestionResult
import com.flip.integration.ocr.application.GradingOcrResult
import com.flip.integration.ocr.application.GradingOcrVerdict
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class FlipAiGradingOcrClient(
    @Value("\${flip-ai.api.url}")
    private val apiUrl: String,
    restClientBuilder: RestClient.Builder
) : GradingOcrClient {

    private val restClient = restClientBuilder
        .baseUrl(apiUrl)
        .build()

    override fun grade(worksheetSource: WorksheetSource, worksheetTitle: String, imageBase64: String): GradingOcrResult {
        val request = GradingOcrRequest(
            workSheetSource = toApiValue(worksheetSource),
            name = worksheetTitle,
            imageBase64 = imageBase64
        )

        val response = restClient.post()
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .body(GradingOcrRawResponse::class.java)
            ?: throw IllegalStateException("OCR 서버 응답이 없습니다.")

        return GradingOcrResult(
            pageNo = response.pageNo.orEmpty(),
            holdReason = response.holdReason.orEmpty(),
            results = response.results.orEmpty().map {
                GradingOcrQuestionResult(
                    questionNumber = it.questionNumber.orEmpty(),
                    verdict = toVerdict(it.verdict),
                    studentAnswer = it.studentAnswer.orEmpty()
                )
            }
        )
    }

    private fun toApiValue(source: WorksheetSource): String = when (source) {
        WorksheetSource.INHOUSE -> "exam"
        WorksheetSource.EXTERNAL -> "workbook"
    }

    private fun toVerdict(verdict: String?): GradingOcrVerdict = when (verdict) {
        "O" -> GradingOcrVerdict.CORRECT
        "X" -> GradingOcrVerdict.INCORRECT
        else -> GradingOcrVerdict.HOLD
    }
}
