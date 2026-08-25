package com.flip.domain.student.application

import com.flip.domain.school.application.SchoolReader
import com.flip.domain.student.presentation.CreateStudentRequest
import com.flip.domain.student.presentation.StudentListResponse
import com.flip.domain.student.presentation.StudentResponse
import com.flip.domain.student.presentation.UpdateStudentRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class StudentService(
    private val studentReader: StudentReader,
    private val studentCreator: StudentCreator,
    private val studentValidator: StudentValidator,
    private val studentDeleter: StudentDeleter,
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
        val student = studentCreator.create(
            name = request.name,
            grade = request.grade,
            rawPassword = request.password,
            school = school
        )
        return StudentResponse.from(student)
    }

    @Transactional
    fun update(studentId: Long, request: UpdateStudentRequest): StudentResponse {
        val student = studentReader.getById(studentId)
        request.name?.let { student.name = it }
        request.grade?.let { student.grade = it }
        request.schoolId?.let { student.school = schoolReader.getById(it) }
        request.password?.let { student.password = it }
        return StudentResponse.from(student)
    }

    @Transactional
    fun delete(studentId: Long) {
        val student = studentReader.getById(studentId)
        studentValidator.validateDeletable(studentId)
        studentDeleter.delete(student)
    }
}
