package fun.steven.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.User;

@Repository             /* 标记为 Spring Bean */
public interface UserRepository extends CrudRepository<User, String> {
    
} 
