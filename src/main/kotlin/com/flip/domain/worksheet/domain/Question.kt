package com.flip.domain.worksheet.domain

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

@Entity
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worksheet_id", nullable = false)
    val worksheet: Worksheet,

    @Column(nullable = false)
    var questionNumber: Int,

    @Column(nullable = false)
    var page: Int,

    @Column(nullable = false)
    var correctAnswer: String,

    // FLIP-AI 채점기가 유형별로 다르게 채점(객관식=동그라미 번호, 주관식=SymPy 동치).
    // 기존 데이터 호환을 위해 nullable — 백필 후 nullable=false로 조일 것.
    @Enumerated(EnumType.STRING)
    @Column
    var type: QuestionType? = null,

    // 객관식 선지 수(주관식은 무의미). 없으면 AI가 5(5지선다)로 가정.
    @Column
    var numChoices: Int? = null
) : BaseEntity()
