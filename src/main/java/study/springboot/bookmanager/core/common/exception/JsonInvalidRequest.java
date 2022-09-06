package study.springboot.bookmanager.core.common.exception;

import org.springframework.http.HttpStatus;
import study.springboot.bookmanager.core.common.json.ApiJsonError;

import java.util.List;

public class JsonInvalidRequest extends CommonException {

    public static final String MESSAGE = HttpStatus.BAD_REQUEST.getReasonPhrase();


    public JsonInvalidRequest() {
        super(MESSAGE);
    }

    public JsonInvalidRequest(List<ApiJsonError> errors) {
        super(MESSAGE);
        addError(errors);
    }

    public JsonInvalidRequest(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
