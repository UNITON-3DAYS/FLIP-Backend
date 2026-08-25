package com.flip.integration.ocr.application

import com.flip.domain.worksheet.domain.WorksheetSource

interface GradingOcrClient {
    fun grade(worksheetSource: WorksheetSource, worksheetTitle: String, imageBase64: String): GradingOcrResult
}

data class GradingOcrResult(
    val pageNo: String,
    val holdReason: String,
    val results: List<GradingOcrQuestionResult>
) {
    val isUsable: Boolean
        get() = holdReason.isBlank() && pageNo.isNotBlank()
}

data class GradingOcrQuestionResult(
    val questionNumber: String,
    val verdict: GradingOcrVerdict,
    val studentAnswer: String
)

enum class GradingOcrVerdict {
    CORRECT, INCORRECT, HOLD
}
