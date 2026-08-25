package com.flip.domain.student.application

import com.flip.domain.grading.infrastructure.GradingImageRepository
import com.flip.domain.grading.infrastructure.GradingRecordRepository
import com.flip.domain.grading.infrastructure.GradingResultRepository
import com.flip.domain.student.domain.Student
import com.flip.domain.student.infrastructure.StudentRepository
import org.springframework.stereotype.Component

@Component
class StudentDeleter(
    private val studentRepository: StudentRepository,
    private val gradingRecordRepository: GradingRecordRepository,
    private val gradingImageRepository: GradingImageRepository,
    private val gradingResultRepository: GradingResultRepository
) {
    fun delete(student: Student) {
        val gradingRecordIds = gradingRecordRepository.findIdsByStudentId(student.id!!)

        if (gradingRecordIds.isNotEmpty()) {
            gradingImageRepository.deleteAllByGradingRecordIdIn(gradingRecordIds)
            gradingResultRepository.deleteAllByGradingRecordIdIn(gradingRecordIds)
            gradingRecordRepository.deleteAllByStudentId(student.id!!)
        }

        studentRepository.delete(student)
    }
}
