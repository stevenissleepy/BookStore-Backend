package fun.steven.bookstore.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fun.steven.bookstore.pojo.entity.Order;

public interface IOrderDao {
    Order save(Order order);

    /* 查询所有订单 */
    Page<Order> findAll(Pageable pageable);

    Page<Order> findByTitle(String title, Pageable pageable);

    Page<Order> findByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Page<Order> findByDateRangeAndTitle(LocalDateTime startDate, LocalDateTime endDate,
            String title, Pageable pageable);

    /* 查询用户的订单 */
    Page<Order> findByUserId(Long userId, Pageable pageable);

    Page<Order> findByUserIdAndTitle(Long userId, String title, Pageable pageable);

    Page<Order> findByUserIdAndDateRange(Long userId, LocalDateTime startDate,
            LocalDateTime endDate, Pageable pageable);

    Page<Order> findByUserIdAndDateRangeAndTitle(Long userId, LocalDateTime startDate,
            LocalDateTime endDate, String title, Pageable pageable);

    /* 统计用户购买的书籍 */
    List<Object[]> findByUserId(Long userId);

    List<Object[]> findByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    /* 查询10本销量最高的书 */
    List<Object[]> findTop10Books();

    List<Object[]> findTop10BooksByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    /* 查询10个消费最高的用户 */
    List<Object[]> findTop10Users();

    List<Object[]> findTop10UsersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
