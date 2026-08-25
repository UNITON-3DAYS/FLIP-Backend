package com.flip.domain.school.infrastructure

import com.flip.domain.school.domain.School
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SchoolRepository : JpaRepository<School, Long>
