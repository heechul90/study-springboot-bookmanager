package study.springboot.bookmanager.core.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springboot.bookmanager.core.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
