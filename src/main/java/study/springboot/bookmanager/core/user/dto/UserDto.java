package study.springboot.bookmanager.core.user.dto;

import lombok.Getter;
import study.springboot.bookmanager.core.user.domain.User;

@Getter
public class UserDto {

    private Long userId;
    private String userName;
    private String email;

    public UserDto(User user) {
        this.userId = user.getId();
        this.userName = user.getName();
        this.email = user.getEmail();
    }
}
