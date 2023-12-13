package com.soa.handler;

import com.soa.controller.VehicleController;
import com.soa.controller.VehicleEnginePowerController;
import com.soa.dto.ErrorMessage;
import com.soa.exception.EntityNotFoundException;
import com.soa.exception.NotValidParamsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = {VehicleController.class, VehicleEnginePowerController.class})
public class ProcessExceptionHandler {
    private final ErrorMessage errorMessage = new ErrorMessage();

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.status(400)
                .body(errorMessage.setMessage("Что-то пошло не так."));
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(404)
                .body(errorMessage.setMessage("Запрашиваемый ресурс не найден."));
    }

    @ExceptionHandler({NotValidParamsException.class})
    public ResponseEntity<?> handleNotValidParamsException(NotValidParamsException e) {
        return ResponseEntity.status(400)
                .body(errorMessage.setMessage("Невалидные параметры в запросе."));
    }

    @ExceptionHandler({DateTimeParseException.class})
    public ResponseEntity<?> handleDateTimeParseException(DateTimeParseException e) {
        return ResponseEntity.status(422)
                .body(errorMessage.setMessage("Что-то пошло не так в теле запроса."));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(422)
                .body(errorMessage.setMessage("Что-то пошло не так в теле запроса."));
    }


    @ExceptionHandler({ConstraintViolationException.class, BindException.class, MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handleValidationException(Exception ex) {
        String message = ex.getMessage();
        if (ex instanceof MethodArgumentNotValidException) {
            message = "Некорректные параметры " + getValidationError((MethodArgumentNotValidException) ex);
        }
        return ResponseEntity.badRequest()
                .body(errorMessage.setMessage(message));
    }

    private String getValidationError(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .sorted()
                .collect(Collectors.joining(";"));
    }
}
