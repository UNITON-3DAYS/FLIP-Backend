package com.flip.domain.student.application

import com.flip.domain.student.presentation.StudentListResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class StudentService(
    private val studentReader: StudentReader
) {
    fun getList(): StudentListResponse {
        val students = studentReader.findAll()
        return StudentListResponse.from(students)
    }
}
