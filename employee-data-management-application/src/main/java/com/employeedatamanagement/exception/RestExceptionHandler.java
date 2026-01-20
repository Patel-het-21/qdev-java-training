package com.employeedatamanagement.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;
import tools.jackson.databind.exc.InvalidFormatException;

/**
 * @author Het Patel
 * @since 11/12/25
 * 
 * Global exception handler for REST controllers.
 */
@ControllerAdvice
public class RestExceptionHandler {

	/**
	 * Handles validation errors triggered by {@code @Valid} annotations.
	 *
	 * @param ex      the validation exception
	 * @param request the current web request
	 * @return HTTP 400 response with validation error details
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		String errors = ex.getBindingResult().getFieldErrors().stream()
				.map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).collect(Collectors.joining(", "));
		body.put("errors", errors);
		return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles {@link IllegalArgumentException}.
	 *
	 * @param ex the exception
	 * @return HTTP 400 response with error message
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleBadRequest(IllegalArgumentException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles {@link EntityNotFoundException}.
	 *
	 * @param ex the exception
	 * @return HTTP 404 response with error message
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleNotFound(EntityNotFoundException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles method argument type mismatch errors.
	 * <p>
	 * Example: passing a string instead of a numeric ID.
	 * </p>
	 *
	 * @param ex the type mismatch exception
	 * @return HTTP 400 response with detailed message
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String paramName = ex.getName();
		String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "Unknown Type";
		String message = String.format("Invalid value for '%s'. Expected a value of type '%s'.", paramName,
				requiredType);
		Map<String, Object> body = new HashMap<>();
		body.put("error", message);
		body.put("invalidValue", ex.getValue());
		return ResponseEntity.badRequest().body(body);
	}

	/**
	 * Handles JSON parsing and deserialization errors.
	 * <p>
	 * Provides user-friendly messages for invalid formats, especially date parsing
	 * errors.
	 * </p>
	 *
	 * @param ex the parsing exception
	 * @return HTTP 400 response with format error message
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, Object>> handleJsonParseError(HttpMessageNotReadableException ex) {
		String message = "Invalid input format. Please check your request body.";
		Throwable cause = ex.getCause();
		if (cause instanceof InvalidFormatException ife && ife.getTargetType() == java.time.LocalDate.class) {
			message = "Invalid date format. Expected format: yyyy-MM-dd (e.g., 1990-01-15)";
		}
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", message);
		return ResponseEntity.badRequest().body(body);
	}

	/**
	 * Handles all uncaught exceptions.
	 *
	 * @param ex the exception
	 * @return HTTP 500 response with error message
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAll(Exception ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		body.put("error", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}