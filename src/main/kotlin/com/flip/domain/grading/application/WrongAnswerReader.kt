package com.flip.domain.grading.application

import com.flip.domain.grading.domain.WrongAnswer
import com.flip.domain.grading.infrastructure.WrongAnswerRepository
import org.springframework.stereotype.Component

@Component
class WrongAnswerReader(
    private val wrongAnswerRepository: WrongAnswerRepository
) {
    fun findAllByGradingRecordId(gradingRecordId: Long): List<WrongAnswer> {
        return wrongAnswerRepository.findAllWithQuestionByGradingRecordId(gradingRecordId)
    }
}
