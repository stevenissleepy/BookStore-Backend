package fun.steven.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
