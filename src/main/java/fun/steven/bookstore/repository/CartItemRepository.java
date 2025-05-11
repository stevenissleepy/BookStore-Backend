package fun.steven.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByBookIdAndCartId(Long bookId, Long cartId);
    Integer countByCartId(Long cartId);
    Optional<List<CartItem>> findByCartId(Long cartId);
}
