package com.flip.domain.worksheet.domain

import com.flip.common.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "uk_worksheet_title_source", columnNames = ["title", "source"])
    ]
)
class Worksheet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var source: WorksheetSource
) : BaseEntity()
