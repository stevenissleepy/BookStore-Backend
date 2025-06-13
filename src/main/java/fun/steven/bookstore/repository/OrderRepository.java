package fun.steven.bookstore.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    Optional<List<Order>> findByUserId(Long userId);
    
    // 按日期范围查询
    @Query("SELECT o FROM Order o WHERE o.date >= :startDate AND o.date <= :endDate")
    List<Order> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                               @Param("endDate") LocalDateTime endDate);
    
    // 按书名查询
    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi JOIN oi.book b " +
           "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :bookTitle, '%'))")
    List<Order> findByBookTitle(@Param("bookTitle") String bookTitle);
    
    // 按日期范围和书名查询
    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi JOIN oi.book b " +
           "WHERE o.date >= :startDate AND o.date <= :endDate " +
           "AND b.title LIKE CONCAT('%', :bookTitle, '%')")
    List<Order> findByDateRangeAndBookTitle(@Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate,
                                           @Param("bookTitle") String bookTitle);
}
