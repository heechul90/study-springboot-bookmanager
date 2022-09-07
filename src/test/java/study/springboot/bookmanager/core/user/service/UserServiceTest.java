package study.springboot.bookmanager.core.user.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import study.springboot.bookmanager.core.common.dto.SearchCondition;
import study.springboot.bookmanager.core.user.domain.User;
import study.springboot.bookmanager.core.user.dto.UserSearchCondition;
import study.springboot.bookmanager.core.user.repository.UserQueryRepository;
import study.springboot.bookmanager.core.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    //USER_DATA
    public static final String NAME = "test_user";
    public static final String EMAIL = "test_email";

    //UPDATE_USER_DATA
    public static final String UPDATE_USER = "update_user";
    public static final String UPDATE_EMAIL = "update_email";

    //VALIDATION_MESSAGE

    @InjectMocks UserService userService;

    @Mock UserRepository userRepository;

    @Mock UserQueryRepository userQueryRepository;

    private User getUser(String name, String email) {
        return User.createUserBuilder()
                .name(name)
                .email(email)
                .build();
    }

    @Test
    @DisplayName(value = "유저 목록 조회")
    void findUsersTest() {
        //given
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            users.add(getUser(NAME + i, EMAIL + i));
        }
        given(userQueryRepository.findUsers(any(), any())).willReturn(new PageImpl(users));

        //TODO 검색 적용 안됨
        UserSearchCondition condition = new UserSearchCondition();
        condition.setSearchCondition(SearchCondition.NAME);
        condition.setSearchKeyword("1");
        condition.setSearchEmail("1");
        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        Page<User> content = userService.findUsers(condition, pageRequest);

        //then
        assertThat(content.getTotalElements()).isEqualTo(20);
        assertThat(content.getContent().size()).isEqualTo(20);
        assertThat(content.getContent()).extracting("name").contains(NAME + 0, NAME + 19);

        //verify
        verify(userQueryRepository, times(1)).findUsers(any(), any());
    }

    @Test
    @DisplayName(value = "유저 단건 조회")
    void findUserTest() {
        //given
        User user = getUser(NAME, EMAIL);
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));

        //when
        User findUser = userService.findUser(any());

        //then
        assertThat(findUser.getName()).isEqualTo(NAME);
        assertThat(findUser.getEmail()).isEqualTo(EMAIL);

        //verify
        verify(userRepository, times(1)).findById(any());
    }

    @Test
    @DisplayName(value = "유저 저장")
    void saveUserTest() {
    }

    @Test
    @DisplayName(value = "유저 수정")
    void updateUserTest() {
    }

    @Test
    @DisplayName(value = "유저 삭제")
    void deleteUserTest() {
    }
}