package guru.springframework.msscbrewery.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<String> exceptionHandler(
			MethodArgumentNotValidException e) {
		List<String> errors = new ArrayList<>(
				e.getBindingResult().getErrorCount());
		e.getBindingResult().getGlobalErrors().forEach(error -> {
			errors.add(String.format("%s -> %s", error.getObjectName(),
					error.getDefaultMessage()));
		});
		e.getBindingResult().getFieldErrors().forEach(error -> {
			errors.add(String.format("%s.%s = %s -> %s", error.getObjectName(),
					error.getField(),
					error.getRejectedValue(), error.getDefaultMessage()));
		});
		return errors;
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String exception(IllegalArgumentException ex) {
		return ex.getLocalizedMessage();
	}

	@ExceptionHandler(value = NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void exception(NoSuchElementException ex) {
	}

}
