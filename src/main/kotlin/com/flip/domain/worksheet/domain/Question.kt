package com.flip.domain.worksheet.domain

import com.flip.common.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worksheet_id", nullable = false)
    val worksheet: Worksheet,

    @Column(nullable = false)
    val questionNumber: Int,

    @Column(nullable = false)
    val page: Int,

    @Column(nullable = false)
    val correctAnswer: String
) : BaseEntity()
