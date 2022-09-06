package study.springboot.bookmanager.core.user.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import study.springboot.bookmanager.core.user.domain.User;
import study.springboot.bookmanager.core.user.dto.UserSearchCondition;

import javax.persistence.EntityManager;
import java.util.List;

import static study.springboot.bookmanager.core.user.domain.QUser.user;

@Repository
public class UserQueryRepository {

    private final JPAQueryFactory queryFactory;

    public UserQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<User> findUsers(UserSearchCondition condition, Pageable pageable) {
        List<User> content = queryFactory
                .select(user)
                .from(user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(user.count())
                .from(user);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }
}
