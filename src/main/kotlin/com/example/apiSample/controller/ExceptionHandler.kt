package com.example.apiSample.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

data class ErrorResponse(val error_message: String)

class BadRequestException(override val message: String) : Exception(message)
class UnauthorizedException(override val message: String) : Exception(message)
class NotFoundException(override val message: String) : Exception(message)

@ControllerAdvice
class apiExceptionHandler {

    @ExceptionHandler(BadRequestException::class)
    fun badRequest(req: HttpServletRequest, e: BadRequestException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(e.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun notAuthorized(req: HttpServletRequest, e: UnauthorizedException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(e.message)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFound(req: HttpServletRequest, e: UnauthorizedException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(e.message)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }
}
