package study.springboot.bookmanager.api.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.springboot.bookmanager.core.common.json.ApiJsonResult;
import study.springboot.bookmanager.core.user.domain.User;
import study.springboot.bookmanager.core.user.dto.UserSearchCondition;
import study.springboot.bookmanager.core.user.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class ApiUsercontroller {

    private final UserService userService;

    /**
     * 유저 목록 조회
     */
    @GetMapping
    public ApiJsonResult findUsers(UserSearchCondition condition, Pageable pageable) {
        Page<User> content = userService.findUsers(condition, pageable);

        return ApiJsonResult.OK();
    }
}
