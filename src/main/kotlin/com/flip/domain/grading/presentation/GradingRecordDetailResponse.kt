package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingResult
import com.flip.domain.grading.domain.GradingResultVerdict
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import kotlin.math.roundToInt

data class GradingRecordDetailResponse(
    @field:Schema(description = "학습지 제목", example = "중1 수학 1단원 연습문제")
    val worksheetTitle: String,

    @field:Schema(description = "채점 기록 생성 일시", example = "2026-08-25T10:00:00")
    val createdAt: LocalDateTime,

    @field:Schema(description = "정답 수", example = "8")
    val correctCount: Int,

    @field:Schema(description = "전체 문항 수", example = "10")
    val totalCount: Int,

    @field:Schema(description = "점수 (0~100)", example = "80")
    val score: Int,

    @field:Schema(description = "오답 목록")
    val wrongAnswers: List<WrongAnswerResponse>
) {
    companion object {
        fun of(gradingRecord: GradingRecord, gradingResults: List<GradingResult>) = GradingRecordDetailResponse(
            worksheetTitle = gradingRecord.worksheet.title,
            createdAt = gradingRecord.createdAt!!,
            correctCount = gradingResults.count { it.verdict == GradingResultVerdict.CORRECT },
            totalCount = gradingResults.size,
            score = calculateScore(gradingResults),
            wrongAnswers = gradingResults
                .filter { it.verdict == GradingResultVerdict.INCORRECT }
                .map { WrongAnswerResponse.from(it) }
        )

        private fun calculateScore(gradingResults: List<GradingResult>): Int {
            if (gradingResults.isEmpty()) return 0
            val correctCount = gradingResults.count { it.verdict == GradingResultVerdict.CORRECT }
            return (correctCount * 100.0 / gradingResults.size).roundToInt()
        }
    }
}
