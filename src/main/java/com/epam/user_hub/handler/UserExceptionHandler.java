package com.epam.user_hub.handler;

import com.epam.user_hub.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class UserExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getBindingResult()
                .getFieldErrors();

        Map<String,Object> errorMap = errors
                .stream()
                .collect(Collectors.toMap(x->x.getField(), x->x.getDefaultMessage()));

        ProblemDetail problemDetail = ProblemDetail.
                forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request is invalid");

        problemDetail.setProperties(errorMap);
        return problemDetail;

    }

    @ExceptionHandler(UserException.class)
    public ProblemDetail handleUserException(UserException ex) {
        ProblemDetail problemDetail = ProblemDetail.
                forStatusAndDetail(HttpStatus.CONFLICT, "Request is invalid");
        problemDetail.setDetail(ex.getMessage());

        return problemDetail;
    }
}
