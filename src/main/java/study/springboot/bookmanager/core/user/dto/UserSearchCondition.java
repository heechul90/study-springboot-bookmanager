package study.springboot.bookmanager.core.user.dto;

import lombok.Getter;
import lombok.Setter;
import study.springboot.bookmanager.core.common.dto.CommonSearchCondition;

@Getter
@Setter
public class UserSearchCondition extends CommonSearchCondition {

    private String searchEmail;

}
