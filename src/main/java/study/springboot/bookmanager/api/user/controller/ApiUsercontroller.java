package study.springboot.bookmanager.api.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.springboot.bookmanager.api.user.request.CreateUserRequest;
import study.springboot.bookmanager.api.user.request.UpdateUserRequest;
import study.springboot.bookmanager.api.user.response.CreateUserResponse;
import study.springboot.bookmanager.api.user.response.UpdateUserResponse;
import study.springboot.bookmanager.core.common.json.ApiJsonResult;
import study.springboot.bookmanager.core.user.domain.User;
import study.springboot.bookmanager.core.user.dto.UserDto;
import study.springboot.bookmanager.core.user.dto.UserSearchCondition;
import study.springboot.bookmanager.core.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

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
        List<UserDto> users = content.getContent().stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toList());

        return ApiJsonResult.OK(users);
    }

    /**
     * 유저 단건 조회
     */
    @GetMapping(value = "/{id}")
    public ApiJsonResult findUser(@PathVariable("id") Long userId) {
        User findUser = userService.findUser(userId);
        UserDto user = new UserDto(findUser);
        return ApiJsonResult.OK(user);
    }

    /**
     * 유저 저장
     */
    @PostMapping
    public ApiJsonResult saveUser(@RequestBody @Validated CreateUserRequest request) {

        //validate
        request.validate();

        User savedUser = userService.saveUser(request.toEntity());

        return ApiJsonResult.OK(new CreateUserResponse(savedUser.getId()));
    }

    /**
     * 유저 수정
     */
    @PutMapping(value = "{id}")
    public ApiJsonResult updateUser(@PathVariable("id") Long userId,
                                    @RequestBody @Validated UpdateUserRequest request) {

        //validate
        request.validate();

        userService.updateUser(userId, request.toParam());
        User updatedUser = userService.findUser(userId);

        return ApiJsonResult.OK(new UpdateUserResponse(updatedUser.getId()));
    }

    /**
     * 유저 삭제
     */
    @DeleteMapping(value = "{id}")
    public ApiJsonResult deleteUser(@PathVariable("id") Long userId) {

        userService.deleteUser(userId);

        return ApiJsonResult.OK();
    }
}
