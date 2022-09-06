package study.springboot.bookmanager.core.common.json;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiJsonError {

    private String fieldName;
    private String errorMessage;

    @Builder
    public ApiJsonError(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

}
