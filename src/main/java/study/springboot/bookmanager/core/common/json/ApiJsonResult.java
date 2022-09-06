package study.springboot.bookmanager.core.common.json;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ApiJsonResult<T> {

    private LocalDateTime transaction_time;
    private HttpStatus status;
    private String message;

    private List<ApiJsonError> errors;
    private T data;

    public static <T> ApiJsonResult<T> OK() {
        return (ApiJsonResult<T>) ApiJsonResult.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.OK)
                .build();
    }

    public static <T> ApiJsonResult<T> OK(T data) {
        return (ApiJsonResult<T>) ApiJsonResult.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.OK)
                .data(data)
                .build();
    }

    public static <T> ApiJsonResult<T> ERROR(HttpStatus status, String message, List<ApiJsonError> errors) {
        return (ApiJsonResult<T>) ApiJsonResult.builder()
                .transaction_time(LocalDateTime.now())
                .status(status)
                .message(message)
                .errors(errors)
                .build();
    }

}
