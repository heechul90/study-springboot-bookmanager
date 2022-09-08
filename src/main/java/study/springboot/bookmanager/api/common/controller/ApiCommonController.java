package study.springboot.bookmanager.api.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.springboot.bookmanager.core.common.exception.CommonException;
import study.springboot.bookmanager.core.common.json.ApiJsonError;
import study.springboot.bookmanager.core.common.json.ApiJsonResult;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiCommonController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiJsonResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<ApiJsonError> errors = e.getFieldErrors().stream()
                .map(error -> new ApiJsonError(
                        error.getField(),
                        error.getDefaultMessage()
                )).collect(Collectors.toList());
        return ApiJsonResult.ERROR(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    }

    @ExceptionHandler(CommonException.class)
    public ApiJsonResult commonException(CommonException e) {
        return ApiJsonResult.ERROR(e.httpStatus(), e.getMessage(), e.getErrors());
    }

}
