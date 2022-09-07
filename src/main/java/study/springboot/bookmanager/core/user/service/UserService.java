package study.springboot.bookmanager.core.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.bookmanager.core.common.exception.EntityNotFound;
import study.springboot.bookmanager.core.user.domain.User;
import study.springboot.bookmanager.core.user.dto.UpdateUserParam;
import study.springboot.bookmanager.core.user.dto.UserSearchCondition;
import study.springboot.bookmanager.core.user.repository.UserQueryRepository;
import study.springboot.bookmanager.core.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    public static final String ENTITY_NAME = "User";
    private final UserQueryRepository userQueryRepository;
    private final UserRepository userRepository;

    /**
     * 유저 목록 조회
     */
    public Page<User> findUsers(UserSearchCondition condition, Pageable pageable) {
        return userQueryRepository.findUsers(condition, pageable);
    }

    /**
     * 유저 단건 조회
     */
    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFound(ENTITY_NAME, userId));
    }

    /**
     * 유저 저장
     */
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 유저 수정
     */
    public void updateUser(Long userId, UpdateUserParam param) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFound(ENTITY_NAME, userId));
        findUser.updateUserBuilder()
                .param(param)
                .build();
    }

    /**
     * 유저 삭제
     */
    @Transactional
    public void deleteUser(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFound(ENTITY_NAME, userId));
        userRepository.delete(findUser);
    }

}
