package com.flip.domain.grading.presentation

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.WrongAnswer
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

data class GradingRecordDetailResponse(
    val worksheetTitle: String,
    val createdAt: String,
    val correctCount: Int,
    val totalCount: Int,
    val score: Int,
    val wrongAnswers: List<WrongAnswerResponse>
) {
    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.KOREAN)

        fun of(gradingRecord: GradingRecord, wrongAnswers: List<WrongAnswer>) = GradingRecordDetailResponse(
            worksheetTitle = gradingRecord.worksheet.title,
            createdAt = gradingRecord.createdAt!!.format(DATE_FORMATTER),
            correctCount = gradingRecord.correctCount,
            totalCount = gradingRecord.totalCount,
            score = calculateScore(gradingRecord.correctCount, gradingRecord.totalCount),
            wrongAnswers = wrongAnswers.map { WrongAnswerResponse.from(it) }
        )

        private fun calculateScore(correctCount: Int, totalCount: Int): Int {
            if (totalCount == 0) return 0
            return (correctCount * 100.0 / totalCount).roundToInt()
        }
    }
}
