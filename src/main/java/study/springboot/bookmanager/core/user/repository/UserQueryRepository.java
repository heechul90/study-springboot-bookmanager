package study.springboot.bookmanager.core.user.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.springboot.bookmanager.core.common.dto.SearchCondition;
import study.springboot.bookmanager.core.user.domain.User;
import study.springboot.bookmanager.core.user.dto.UserSearchCondition;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.*;
import static study.springboot.bookmanager.core.user.domain.QUser.user;

@Repository
public class UserQueryRepository {

    private final JPAQueryFactory queryFactory;

    public UserQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<User> findUsers(UserSearchCondition condition, Pageable pageable) {
        List<User> content = getUserList(condition, pageable);

        JPAQuery<Long> count = getUserListCount();

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    /**
     * 유저 목록
     */
    private List<User> getUserList(UserSearchCondition condition, Pageable pageable) {
        List<User> content = queryFactory
                .select(user)
                .from(user)
                .where(
                        searchCondition(condition.getSearchCondition(), condition.getSearchKeyword()),
                        searchEmailLike(condition.getSearchEmail())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return content;
    }

    /**
     * 유저 목록 카운트
     */
    private JPAQuery<Long> getUserListCount() {
        JPAQuery<Long> count = queryFactory
                .select(user.count())
                .from(user);
        return count;
    }

    /**
     * name like '%searchKeyword%'
     */
    private BooleanExpression searchCondition(SearchCondition searchCondition, String searchKeyword) {
        if (searchCondition == null || !hasText(searchKeyword)) {
            return null;
        }

        if (searchCondition.equals(SearchCondition.NAME)) {
            return user.name.contains(searchKeyword);
        } else {
            return null;
        }
    }

    /**
     * mail like '%searchMail%'
     */
    private BooleanExpression searchEmailLike(String searchEmail) {
        if (!hasText(searchEmail)) {
            return null;
        }
        return user.email.contains(searchEmail);
    }
}
