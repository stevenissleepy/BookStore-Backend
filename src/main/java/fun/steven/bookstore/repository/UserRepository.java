package fun.steven.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.User;

@Repository             /* 标记为 Spring Bean */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
} 
