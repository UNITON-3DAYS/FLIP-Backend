package com.flip.domain.student.infrastructure

import com.flip.domain.student.domain.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Long> {
    fun existsBySchoolId(schoolId: Long): Boolean

    @Query("SELECT s FROM Student s JOIN FETCH s.school")
    fun findAllWithSchool(): List<Student>
}
