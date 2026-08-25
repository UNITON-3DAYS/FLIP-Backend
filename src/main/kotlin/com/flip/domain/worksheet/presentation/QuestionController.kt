package com.flip.domain.worksheet.presentation

import com.flip.domain.worksheet.application.QuestionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/worksheets/{worksheetId}/questions")
@Tag(name = "Question", description = "문항 관련 API입니다.")
class QuestionController(
    private val questionService: QuestionService
) {
    @PostMapping
    @Operation(summary = "문항 생성", description = "문제지에 새로운 문항을 추가합니다.")
    fun create(
        @PathVariable worksheetId: Long,
        @RequestBody @Valid request: CreateQuestionRequest
    ): ResponseEntity<QuestionResponse> {
        val response = questionService.create(worksheetId, request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping
    @Operation(summary = "문항 목록 조회", description = "문제지에 속한 문항 목록을 조회합니다.")
    fun getList(
        @PathVariable worksheetId: Long
    ): ResponseEntity<QuestionListResponse> {
        val response = questionService.getList(worksheetId)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PatchMapping("/{questionId}")
    @Operation(summary = "문항 수정", description = "문항 정보를 수정합니다.")
    fun update(
        @PathVariable worksheetId: Long,
        @PathVariable questionId: Long,
        @RequestBody @Valid request: UpdateQuestionRequest
    ): ResponseEntity<QuestionResponse> {
        val response = questionService.update(worksheetId, questionId, request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/{questionId}")
    @Operation(summary = "문항 삭제", description = "문항을 삭제합니다.")
    fun delete(
        @PathVariable worksheetId: Long,
        @PathVariable questionId: Long
    ): ResponseEntity<Unit> {
        questionService.delete(worksheetId, questionId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
