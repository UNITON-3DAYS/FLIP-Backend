package com.flip.domain.grading.domain

import com.flip.common.domain.BaseEntity
import com.flip.domain.student.domain.Student
import com.flip.domain.worksheet.domain.Worksheet
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class GradingRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    val student: Student,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worksheet_id", nullable = false)
    val worksheet: Worksheet,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: GradingStatus = GradingStatus.IN_PROGRESS
) : BaseEntity() {

    fun startGrading() {
        this.status = GradingStatus.GRADING
    }

    fun complete() {
        this.status = GradingStatus.COMPLETED
    }
}
