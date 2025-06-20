package fun.steven.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // 获取用户session信息
    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    Long findUserSessionById(@Param("username") String username);

    // 安全地查询所有用户信息，排除 admin 用户
    @Query("SELECT u.username, u.email ,u.avatar, u.balance, ua.state " +
            "FROM User u LEFT JOIN u.userAuth ua " +
            "WHERE u.username != 'admin'")
    List<Object[]> findAllUsersSafely();

    // 安全地检查用户名是否存在
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username")
    boolean existsByUsername(@Param("username") String username);

    // 安全地检查邮箱是否存在
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);

    // 安全地检查用户名密码是否正确
    @Query("SELECT COUNT(u) > 0 FROM User u JOIN u.userAuth ua " +
            "WHERE u.username = :username AND ua.password = :password")
    boolean existsByUsernameAndPassword(
            @Param("username") String username,
            @Param("password") String password);

    // 安全地检查用户是否被封禁
    @Query("SELECT COUNT(u) > 0 FROM User u JOIN u.userAuth ua " +
            "WHERE u.username = :username AND ua.state = 'banned'")
    boolean existsByUsernameAndBanned(@Param("username") String username);
}
