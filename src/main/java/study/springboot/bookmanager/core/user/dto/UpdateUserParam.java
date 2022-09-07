package study.springboot.bookmanager.core.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateUserParam {

    private String name;
    private String email;

    @Builder
    public UpdateUserParam(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
