package com.flip.domain.student.domain

import com.flip.common.domain.BaseEntity
import com.flip.domain.school.domain.School
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    val school: School,

    @Column(nullable = false)
    var grade: Int,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var password: String
) : BaseEntity()
