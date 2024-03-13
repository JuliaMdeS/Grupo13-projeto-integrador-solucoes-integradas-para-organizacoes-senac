package com.hemoclick.api.exceptions;

public record ExceptionResponseDTO(String message, int statusCode, String exceptionType) {
}
