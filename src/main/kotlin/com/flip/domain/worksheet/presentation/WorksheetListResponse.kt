package com.flip.domain.worksheet.presentation

import com.flip.domain.worksheet.domain.Worksheet
import com.flip.domain.worksheet.domain.WorksheetSource
import io.swagger.v3.oas.annotations.media.Schema

data class WorksheetListResponse(
    @field:Schema(description = "문제지 목록")
    val worksheets: List<WorksheetResponse>
) {
    companion object {
        fun from(worksheets: List<Worksheet>) = WorksheetListResponse(
            worksheets = worksheets.map { WorksheetResponse.from(it) }
        )
    }
}

data class WorksheetResponse(
    @field:Schema(description = "문제지 ID", example = "1")
    val worksheetId: Long,

    @field:Schema(description = "문제지 유형", example = "INHOUSE")
    val source: WorksheetSource,

    @field:Schema(description = "문제지 타이틀", example = "중1 수학 1단원 연습문제")
    val title: String
) {
    companion object {
        fun from(worksheet: Worksheet) = WorksheetResponse(
            worksheetId = worksheet.id!!,
            source = worksheet.source,
            title = worksheet.title
        )
    }
}
