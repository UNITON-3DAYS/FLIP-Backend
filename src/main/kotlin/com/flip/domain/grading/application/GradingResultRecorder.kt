package com.flip.domain.grading.application

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.domain.GradingResult
import com.flip.domain.grading.domain.GradingResultVerdict
import com.flip.domain.grading.infrastructure.GradingResultRepository
import com.flip.integration.ocr.application.GradingOcrResult
import com.flip.integration.ocr.application.GradingOcrVerdict
import org.springframework.stereotype.Component

@Component
class GradingResultRecorder(
    private val gradingResultRepository: GradingResultRepository
) {
    fun record(gradingRecord: GradingRecord, ocrResult: GradingOcrResult) {
        if (!ocrResult.isUsable) return

        ocrResult.results
            .filter { it.verdict != GradingOcrVerdict.HOLD }
            .forEach { result ->
                upsert(
                    gradingRecord = gradingRecord,
                    page = ocrResult.pageNo,
                    questionNumber = result.questionNumber,
                    verdict = toVerdict(result.verdict),
                    studentAnswer = result.studentAnswer
                )
            }
    }

    private fun upsert(
        gradingRecord: GradingRecord,
        page: String,
        questionNumber: String,
        verdict: GradingResultVerdict,
        studentAnswer: String
    ) {
        val existing = gradingResultRepository.findByGradingRecordIdAndPageAndQuestionNumber(
            gradingRecord.id!!, page, questionNumber
        )

        if (existing != null) {
            existing.update(verdict, studentAnswer)
        } else {
            gradingResultRepository.save(
                GradingResult(
                    gradingRecord = gradingRecord,
                    page = page,
                    questionNumber = questionNumber,
                    verdict = verdict,
                    studentAnswer = studentAnswer
                )
            )
        }
    }

    private fun toVerdict(verdict: GradingOcrVerdict): GradingResultVerdict = when (verdict) {
        GradingOcrVerdict.CORRECT -> GradingResultVerdict.CORRECT
        GradingOcrVerdict.INCORRECT -> GradingResultVerdict.INCORRECT
        GradingOcrVerdict.HOLD -> throw IllegalStateException("HOLD 결과는 저장하지 않습니다.")
    }
}
