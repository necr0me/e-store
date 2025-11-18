package by.necr0me.estore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = handleValidationExceptions(ex);
        return ResponseEntity.unprocessableEntity().body(problemDetail);
    }

    private ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatusCode(), "An error occurred during validation");
        problemDetail.setType(URI.create("http://localhost:8080/errors/validation-error"));
        problemDetail.setTitle("Validation error");
        problemDetail.setInstance(ex.getBody().getInstance());
        problemDetail.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        problemDetail.setProperty("errors", getValidationErrors(ex));
        return problemDetail;
    }

    private List<HashMap<String, String>> getValidationErrors(MethodArgumentNotValidException ex) {
        List<HashMap<String, String>> errors = new ArrayList<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new HashMap<>() {
                {
                    put("detail", errorMessage);
                    put("pointer", "#/" + fieldName);
                }
            });
        });

        return errors;
    }
}
