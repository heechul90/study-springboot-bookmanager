package study.springboot.bookmanager.core.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.springboot.bookmanager.core.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "hc_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    //===생성 메서드===//
    /**
     * User 생성
     */
    @Builder(builderClassName = "createUserBuilder", builderMethodName = "createUserBuilder")
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * User 수정
     */
    @Builder(builderClassName = "updateUserBuilder", builderMethodName = "updateUserBuilder")
    public void updateUser(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
