package guru.springframework.msscbrewery.exception;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<?> exception(NoSuchElementException ex) {
		return ResponseEntity.noContent().build();
	}

}
