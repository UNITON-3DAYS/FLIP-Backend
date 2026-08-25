package com.flip.domain.student.infrastructure

import com.flip.domain.student.domain.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Long>
