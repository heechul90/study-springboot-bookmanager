package study.springboot.bookmanager.core.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import study.springboot.bookmanager.core.common.json.ApiJsonError;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class CommonException extends RuntimeException {

    List<ApiJsonError> errors = new ArrayList<>();

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract HttpStatus httpStatus();

    public void addError(List<ApiJsonError> errors) {
        this.errors = errors;
    }
}
