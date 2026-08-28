package com.flip.domain.worksheet.application

import com.flip.domain.worksheet.infrastructure.QuestionRepository
import com.flip.domain.worksheet.presentation.CreateQuestionRequest
import com.flip.domain.worksheet.presentation.QuestionListResponse
import com.flip.domain.worksheet.presentation.QuestionResponse
import com.flip.domain.worksheet.presentation.UpdateQuestionRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QuestionService(
    private val worksheetReader: WorksheetReader,
    private val questionReader: QuestionReader,
    private val questionCreator: QuestionCreator,
    private val questionValidator: QuestionValidator,
    private val questionRepository: QuestionRepository
) {
    @Transactional
    fun create(worksheetId: Long, request: CreateQuestionRequest): QuestionResponse {
        val worksheet = worksheetReader.getById(worksheetId)
        questionValidator.validateNotDuplicated(worksheetId, request.page, request.questionNumber)
        val question = questionCreator.create(
            worksheet = worksheet,
            questionNumber = request.questionNumber,
            page = request.page,
            correctAnswer = request.correctAnswer,
            type = request.type,
            numChoices = request.numChoices
        )
        return QuestionResponse.from(question)
    }

    fun getList(worksheetId: Long): QuestionListResponse {
        worksheetReader.getById(worksheetId)
        val questions = questionReader.findAllByWorksheetId(worksheetId)
        return QuestionListResponse.from(questions)
    }

    @Transactional
    fun update(worksheetId: Long, questionId: Long, request: UpdateQuestionRequest): QuestionResponse {
        worksheetReader.getById(worksheetId)
        val question = questionReader.getById(questionId)
        questionValidator.validateBelongsToWorksheet(question, worksheetId)
        if (question.page != request.page || question.questionNumber != request.questionNumber) {
            questionValidator.validateNotDuplicated(worksheetId, request.page, request.questionNumber)
        }
        question.questionNumber = request.questionNumber
        question.page = request.page
        question.correctAnswer = request.correctAnswer
        question.type = request.type
        question.numChoices = request.numChoices
        return QuestionResponse.from(question)
    }

    @Transactional
    fun delete(worksheetId: Long, questionId: Long) {
        worksheetReader.getById(worksheetId)
        val question = questionReader.getById(questionId)
        questionValidator.validateBelongsToWorksheet(question, worksheetId)
        questionRepository.delete(question)
    }
}
