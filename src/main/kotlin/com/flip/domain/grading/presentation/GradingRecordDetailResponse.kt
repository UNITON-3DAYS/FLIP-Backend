package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingResult
import com.flip.domain.grading.domain.GradingResultVerdict
import java.time.LocalDateTime
import kotlin.math.roundToInt

data class GradingRecordDetailResponse(
    val worksheetTitle: String,
    val createdAt: LocalDateTime,
    val correctCount: Int,
    val totalCount: Int,
    val score: Int,
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
