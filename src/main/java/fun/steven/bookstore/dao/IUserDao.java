package fun.steven.bookstore.dao;

import fun.steven.bookstore.pojo.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserDao {
    boolean save(User user);

    boolean deleteById(Long userId);

    Optional<User> findById(Long userId);
    Optional<User> findByUsername(String username);
    List<Object[]> findAllSafely();

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsernameAndPassword(String username, String password);
    boolean existsByUsernameAndBanned(String username);
}
