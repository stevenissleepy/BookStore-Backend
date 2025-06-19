package fun.steven.bookstore.repository;

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

}
