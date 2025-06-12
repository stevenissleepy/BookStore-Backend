package fun.steven.bookstore.repository;

import java.util.List;
import java.util.Optional;

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
    Optional<List<Book>> findByQuery(@Param("query") String query);

    /* 根据分类搜索 */
    @Query("SELECT b FROM Book b WHERE " +
            "b.category IN :categories")
    Optional<List<Book>> findByCategories(@Param("categories") List<String> categories);

    /* 根据书名和分类搜索 */
    @Query("SELECT b FROM Book b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "AND b.category IN :categories")
    Optional<List<Book>> findByQueryAndCategories(
            @Param("query") String query,
            @Param("categories") List<String> categories);
}
