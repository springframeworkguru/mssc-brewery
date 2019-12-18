package guru.springframework.msscbrewery.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> exceptionHandler(MethodArgumentNotValidException e) {
		List<String> errors = new ArrayList<>(e.getBindingResult().getErrorCount());
		e.getBindingResult().getGlobalErrors().forEach(error -> {
			errors.add(String.format("%s -> %s", error.getObjectName(), error.getDefaultMessage()));
		});
		e.getBindingResult().getFieldErrors().forEach(error -> {
			errors.add(String.format("%s.%s = %s -> %s", error.getObjectName(), error.getField(),
					error.getRejectedValue(), error.getDefaultMessage()));
		});
		return ResponseEntity.badRequest().body(errors);
	}

}
