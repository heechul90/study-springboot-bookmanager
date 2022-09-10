package study.springboot.bookmanager.core.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springboot.bookmanager.core.user.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

}
