package com.flip.domain.grading.domain

import com.flip.common.domain.BaseEntity
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
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["grading_record_id", "page", "question_number"])
    ]
)
class GradingResult(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grading_record_id", nullable = false)
    val gradingRecord: GradingRecord,

    @Column(nullable = false)
    val page: String,

    @Column(name = "question_number", nullable = false)
    val questionNumber: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var verdict: GradingResultVerdict,

    @Column(nullable = false)
    var studentAnswer: String
) : BaseEntity() {

    fun update(verdict: GradingResultVerdict, studentAnswer: String) {
        this.verdict = verdict
        this.studentAnswer = studentAnswer
    }
}
