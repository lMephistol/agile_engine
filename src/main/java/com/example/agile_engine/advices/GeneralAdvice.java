package com.example.agile_engine.advices;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

import com.example.agile_engine.model.ApiError;
import com.example.agile_engine.model.ApiValidationError;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.ValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GeneralAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        return ApiError.newBuilder()
                .withStatus(BAD_REQUEST)
                .withTimestamp(LocalDateTime.now(Clock.systemUTC()))
                .withErrorList(convertError(ex.getBindingResult()))
                .build();
    }

    @ResponseStatus(CONFLICT)
    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public ApiError handleValidationException(ValidationException ex)
    {
        return ApiError.newBuilder()
                .withStatus(BAD_REQUEST)
                .withMessage(ex.getMessage())
                .withTimestamp(LocalDateTime.now(Clock.systemUTC()))
                .build();
    }

    private List<ApiValidationError> convertError(BindingResult result)
    {
        return Stream.concat(result.getGlobalErrors()
                        .stream()
                        .map(this::toApiValidationError),
                result.getFieldErrors()
                        .stream()
                        .map(this::toApiValidationError))
                .collect(Collectors.toList());
    }

    private ApiValidationError toApiValidationError(FieldError fieldError)
    {
        return new ApiValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
    }

    private ApiValidationError toApiValidationError(ObjectError objectError)
    {
        return new ApiValidationError(objectError.getObjectName(), null, null, objectError.getDefaultMessage());
    }

}
