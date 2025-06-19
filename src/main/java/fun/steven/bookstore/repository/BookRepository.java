package fun.steven.bookstore.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

        /* 根据书名搜索 */
        @Query("SELECT b FROM Book b WHERE " +
                        "LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))")
        Page<Book> findByQuery(@Param("query") String query, Pageable pageable);

        /* 根据分类搜索 */
        @Query("SELECT b FROM Book b WHERE " +
                        "b.category IN :categories")
        Page<Book> findByCategories(@Param("categories") List<String> categories, Pageable pageable);

        /* 根据书名和分类搜索 */
        @Query("SELECT b FROM Book b WHERE " +
                        "LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
                        "AND b.category IN :categories")
        Page<Book> findByQueryAndCategories(
                        @Param("query") String query,
                        @Param("categories") List<String> categories,
                        Pageable pageable);

        /* 获取 categories */
        @Query("SELECT DISTINCT b.category FROM Book b")
        List<String> findDistinctCategories();

        /* 统计所有订单中书籍的销量并排序 */
        @Query(value = "SELECT b.title, SUM(oi.quantity) as total_sales " +
                        "FROM tb_book b " +
                        "JOIN tb_order_item oi ON b.id = oi.book_id " +
                        "JOIN tb_order o ON oi.order_id = o.id " +
                        "GROUP BY b.id, b.title " +
                        "ORDER BY total_sales DESC " +
                        "LIMIT 10", nativeQuery = true)
        List<Object[]> findTop10Book();

        /* 按日期范围统计书籍销量 */
        @Query(value = "SELECT b.title, SUM(oi.quantity) as total_sales " +
                        "FROM tb_book b " +
                        "JOIN tb_order_item oi ON b.id = oi.book_id " +
                        "JOIN tb_order o ON oi.order_id = o.id " +
                        "WHERE o.date >= :startDate AND o.date <= :endDate " +
                        "GROUP BY b.id, b.title " +
                        "ORDER BY total_sales DESC " +
                        "LIMIT 10", nativeQuery = true)
        List<Object[]> findTop10BookByDateRange(
                        @Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);
}
