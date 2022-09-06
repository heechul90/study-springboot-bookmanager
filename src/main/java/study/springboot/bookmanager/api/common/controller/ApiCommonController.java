package study.springboot.bookmanager.api.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.springboot.bookmanager.core.common.json.Error;
import study.springboot.bookmanager.core.common.json.JsonResult;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiCommonController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<Error> errors = e.getFieldErrors().stream()
                .map(error -> new Error(
                        error.getField(),
                        error.getDefaultMessage()
                )).collect(Collectors.toList());
        return JsonResult.ERROR(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    }


}
