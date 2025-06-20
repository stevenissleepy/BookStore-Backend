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
            "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :bookTitle, '%'))")
    Page<Order> findByBookTitle(@Param("bookTitle") String bookTitle, Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi JOIN oi.book b " +
            "WHERE o.date >= :startDate AND o.date <= :endDate " +
            "AND b.title LIKE CONCAT('%', :bookTitle, '%')")
    Page<Order> findByDateRangeAndBookTitle(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("bookTitle") String bookTitle,
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
            "WHERE o.user.id = :userId AND LOWER(b.title) LIKE LOWER(CONCAT('%', :bookTitle, '%'))")
    Page<Order> findByUserIdAndBookTitle(
            @Param("userId") Long userId,
            @Param("bookTitle") String bookTitle,
            Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderItems oi JOIN oi.book b " +
            "WHERE o.user.id = :userId AND o.date >= :startDate AND o.date <= :endDate " +
            "AND LOWER(b.title) LIKE LOWER(CONCAT('%', :bookTitle, '%'))")
    Page<Order> findByUserIdAndDateRangeAndBookTitle(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("bookTitle") String bookTitle,
            Pageable pageable);

    /* 统计用户购买了那些书籍 */
    @Query("SELECT oi.book, SUM(oi.quantity) " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "WHERE o.user.id = :userId " +
            "GROUP BY oi.book " +
            "ORDER BY SUM(oi.quantity) DESC")
    List<Object[]> findBooksByUserId(@Param("userId") Long userId);

    @Query("SELECT oi.book, SUM(oi.quantity) " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "WHERE o.user.id = :userId " +
            "AND o.date >= :startDate AND o.date <= :endDate " +
            "GROUP BY oi.book " +
            "ORDER BY SUM(oi.quantity) DESC")
    List<Object[]> findBooksByUserIdAndDateRange(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    /* 查询10本销量最高的书 */
    @Query(value = "SELECT b.title, SUM(oi.quantity) as sales " +
            "FROM tb_book b " +
            "JOIN tb_order_item oi ON b.id = oi.book_id " +
            "JOIN tb_order o ON oi.order_id = o.id " +
            "GROUP BY b.id, b.title " +
            "ORDER BY sales DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10Book();

    @Query(value = "SELECT b.title, SUM(oi.quantity) as sales " +
            "FROM tb_book b " +
            "JOIN tb_order_item oi ON b.id = oi.book_id " +
            "JOIN tb_order o ON oi.order_id = o.id " +
            "WHERE o.date >= :startDate AND o.date <= :endDate " +
            "GROUP BY b.id, b.title " +
            "ORDER BY sales DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10BookByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    /* 查询10个消费最高的用户 */
    @Query(value = "SELECT u.username, SUM(o.total_price) as sales " +
            "FROM tb_user u " +
            "JOIN tb_order o ON u.id = o.user_id " +
            "GROUP BY u.id, u.username " +
            "ORDER BY sales DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10User();

    @Query(value = "SELECT u.username, SUM(o.total_price) as sales " +
            "FROM tb_user u " +
            "JOIN tb_order o ON u.id = o.user_id " +
            "WHERE o.date >= :startDate AND o.date <= :endDate " +
            "GROUP BY u.id, u.username " +
            "ORDER BY sales DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10UserByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
