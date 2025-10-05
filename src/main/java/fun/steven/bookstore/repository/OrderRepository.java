package fun.steven.bookstore.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /* 查询所有订单 */
    @Query("SELECT o FROM Order o WHERE o.date >= :startDate AND o.date <= :endDate")
    Page<Order> findByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi JOIN oi.book b " +
            "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Order> findByTitle(@Param("title") String title, Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi JOIN oi.book b " +
            "WHERE o.date >= :startDate AND o.date <= :endDate " +
            "AND b.title LIKE CONCAT('%', :title, '%')")
    Page<Order> findByDateRangeAndTitle(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("title") String title,
            Pageable pageable);

    /* 查询用户的订单 */
    Page<Order> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.date >= :startDate AND o.date <= :endDate")
    Page<Order> findByUserIdAndDateRange(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi JOIN oi.book b " +
            "WHERE o.user.id = :userId AND LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Order> findByUserIdAndTitle(
            @Param("userId") Long userId,
            @Param("title") String title,
            Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi JOIN oi.book b " +
            "WHERE o.user.id = :userId AND o.date >= :startDate AND o.date <= :endDate " +
            "AND LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Order> findByUserIdAndDateRangeAndTitle(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("title") String title,
            Pageable pageable);

    /* 统计用户购买了那些书籍 */
    @Query("SELECT oi.book, SUM(oi.quantity) " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "WHERE o.user.id = :userId " +
            "GROUP BY oi.book " +
            "ORDER BY SUM(oi.quantity) DESC")
    List<Object[]> findByUserId(@Param("userId") Long userId);

    @Query("SELECT oi.book, SUM(oi.quantity) " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "WHERE o.user.id = :userId " +
            "AND o.date >= :startDate AND o.date <= :endDate " +
            "GROUP BY oi.book " +
            "ORDER BY SUM(oi.quantity) DESC")
    List<Object[]> findByUserIdAndDateRange(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    /* 查询10本销量最高的书 */
    @Query("SELECT oi.book.title, SUM(oi.quantity) " +
            "FROM OrderItem oi " +
            "GROUP BY oi.book.id, oi.book.title " +
            "ORDER BY SUM(oi.quantity) DESC")
    List<Object[]> findTop10Book();

    @Query("SELECT oi.book.title, SUM(oi.quantity) " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "WHERE o.date >= :startDate AND o.date <= :endDate " +
            "GROUP BY oi.book.id, oi.book.title " +
            "ORDER BY SUM(oi.quantity) DESC")
    List<Object[]> findTop10BookByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    /* 查询10个消费最高的用户 */
    @Query("SELECT o.user.username, SUM(o.totalPrice) " +
            "FROM Order o " +
            "GROUP BY o.user.id, o.user.username " +
            "ORDER BY SUM(o.totalPrice) DESC")
    List<Object[]> findTop10User();

    @Query("SELECT o.user.username, SUM(o.totalPrice) " +
            "FROM Order o " +
            "WHERE o.date >= :startDate AND o.date <= :endDate " +
            "GROUP BY o.user.id, o.user.username " +
            "ORDER BY SUM(o.totalPrice) DESC")
    List<Object[]> findTop10UserByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
