package com.flip.integration.ocr.infrastructure

import com.fasterxml.jackson.annotation.JsonProperty

data class GradingOcrRequest(
    @JsonProperty("worksheet_source")
    val workSheetSource: String,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("image_base64")
    val imageBase64: String
)
