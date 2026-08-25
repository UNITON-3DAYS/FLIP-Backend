package com.flip.integration.ocr.infrastructure

import com.fasterxml.jackson.annotation.JsonProperty

data class GradingOcrRawResponse(
    @JsonProperty("page_no")
    val pageNo: String?,
    val results: List<GradingOcrRawResult>?,
    @JsonProperty("hold_reason")
    val holdReason: String?
)

data class GradingOcrRawResult(
    @JsonProperty("question_no")
    val questionNumber: String?,
    val verdict: String?,
    @JsonProperty("student_answer")
    val studentAnswer: String?
)
