package com.flip.domain.student.application

import com.flip.domain.school.application.SchoolReader
import com.flip.domain.student.presentation.CreateStudentRequest
import com.flip.domain.student.presentation.StudentListResponse
import com.flip.domain.student.presentation.StudentResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class StudentService(
    private val studentReader: StudentReader,
    private val studentCreator: StudentCreator,
    private val schoolReader: SchoolReader
) {
    fun getList(): StudentListResponse {
        val students = studentReader.findAll()
        return StudentListResponse.from(students)
    }

    fun getDetail(studentId: Long): StudentResponse {
        val student = studentReader.getById(studentId)
        return StudentResponse.from(student)
    }

    @Transactional
    fun create(request: CreateStudentRequest): StudentResponse {
        val school = schoolReader.getById(request.schoolId)
        val student = studentCreator.create(school, request.grade, request.name, request.password)
        return StudentResponse.from(student)
    }
}
