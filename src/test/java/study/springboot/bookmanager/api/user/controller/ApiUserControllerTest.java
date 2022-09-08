package study.springboot.bookmanager.api.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import study.springboot.bookmanager.core.common.dto.SearchCondition;
import study.springboot.bookmanager.core.user.domain.User;
import study.springboot.bookmanager.core.user.dto.UserSearchCondition;
import study.springboot.bookmanager.core.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(ApiUserController.class)
class ApiUserControllerTest {

    //USER_DATA
    public static final String NAME = "test_user";
    public static final String EMAIL = "test_email";

    //UPDATE_USER_DATA
    public static final String UPDATE_NAME = "update_user";
    public static final String UPDATE_EMAIL = "update_email";

    //VALIDATION_MESSAGE
    public static final String HAS_MESSAGE_STARTING_WITH = "존재하지 않는 User";
    public static final String HAS_MESSAGE_ENDING_WITH = "id = ";

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @MockBean private UserService userService;

    private User getUser(String name, String email) {
        return User.createUserBuilder()
                .name(name)
                .email(email)
                .build();
    }

    @Test
    @DisplayName(value = "유저 목록 조회")
    void findUsers() throws Exception {
        //given
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(getUser(NAME + i, EMAIL + i));
        }
        given(userService.findUsers(any(), any())).willReturn(new PageImpl(users));

        UserSearchCondition condition = new UserSearchCondition();
        condition.setSearchCondition(SearchCondition.NAME);
        condition.setSearchKeyword("1");
        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .param("searchCondition", String.valueOf(condition.getSearchCondition()))
                .param("searchKeyword", condition.getSearchKeyword())
                .param("page", String.valueOf(pageRequest.getOffset()))
                .param("size", String.valueOf(pageRequest.getPageSize()))
        );

        //then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.OK.getReasonPhrase()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()", Matchers.is(10)))
                .andDo(MockMvcResultHandlers.print());

        //verify
        verify(userService, times(1)).findUsers(any(), any());
    }

    @Test
    @DisplayName(value = "유저 단건 조회")
    void findUser() throws Exception {
        //given
        User user = getUser(NAME, EMAIL);
        given(userService.findUser(any())).willReturn(user);

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", 0L)
                .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.OK.getReasonPhrase()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userName").value(NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value(EMAIL))
                .andDo(MockMvcResultHandlers.print());

        //verify
        verify(userService, times(1)).findUser(any());
    }

    @Test
    @DisplayName(value = "유저 저장")
    void saveUser() {
        //given

        //when

        //then

        //verify
    }

    @Test
    @DisplayName(value = "유저 수정")
    void updateUser() {
        //given

        //when

        //then

        //verify
    }

    @Test
    @DisplayName(value = "유저 삭제")
    void deleteUser() {
        //given

        //when

        //then

        //verify
    }
}