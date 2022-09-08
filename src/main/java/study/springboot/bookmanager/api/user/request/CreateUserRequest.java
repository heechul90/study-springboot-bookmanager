package study.springboot.bookmanager.api.user.request;

import lombok.Setter;
import study.springboot.bookmanager.core.common.exception.JsonInvalidRequest;
import study.springboot.bookmanager.core.common.json.ApiJsonError;
import study.springboot.bookmanager.core.user.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter
public class CreateUserRequest {

    @NotBlank
    private String userName;

    @NotBlank
    @Email
    private String userEmail;

    public User toEntity() {
        return User.createUserBuilder()
                .name(this.userName)
                .email(this.userEmail)
                .build();
    }

    public void validate() {
        List<ApiJsonError> errors = new ArrayList<>();

        if (this.userName.contains("바보")) {
            errors.add(new ApiJsonError("userName", "이름에 \"바보\"를 포함할 수 없습니다."));
        }

        if (errors.size() > 0) {
            throw new JsonInvalidRequest(errors);
        }
    }
}
