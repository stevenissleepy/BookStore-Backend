package fun.steven.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.entity.User;

@Repository             /* 标记为 Spring Bean */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query("SELECT u.username, u.email ,u.avatar, u.balance, ua.state " +
        "FROM User u LEFT JOIN u.userAuth ua " +
        "WHERE u.username != 'admin'")
    List<Object[]> findAllUsersSafely();
} 
