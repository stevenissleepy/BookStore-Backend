package fun.steven.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fun.steven.bookstore.pojo.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
