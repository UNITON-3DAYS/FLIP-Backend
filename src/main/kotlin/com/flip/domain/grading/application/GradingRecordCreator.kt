package com.flip.domain.grading.application

import com.flip.domain.grading.domain.GradingRecord
import com.flip.domain.grading.infrastructure.GradingRecordRepository
import com.flip.domain.student.domain.Student
import com.flip.domain.worksheet.domain.Worksheet
import org.springframework.stereotype.Component

@Component
class GradingRecordCreator(
    private val gradingRecordRepository: GradingRecordRepository
) {
    fun create(student: Student, worksheet: Worksheet): GradingRecord {
        return gradingRecordRepository.save(GradingRecord(student = student, worksheet = worksheet))
    }
}
