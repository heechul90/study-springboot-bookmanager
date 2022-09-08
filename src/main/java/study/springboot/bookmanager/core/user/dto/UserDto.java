package study.springboot.bookmanager.core.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

public class UserDto {

    private Long userId;
    private String userName;
    private String email;

    @Builder
    public UserDto(Long userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}
