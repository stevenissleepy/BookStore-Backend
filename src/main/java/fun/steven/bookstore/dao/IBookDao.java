package fun.steven.bookstore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fun.steven.bookstore.pojo.entity.Book;

public interface IBookDao {
    boolean save(Book book);

    boolean delete(Long id);

    Page<Book> findAll(Pageable pageable);
    Page<Book> findByQuery(String query, Pageable pageable);
    Page<Book> findByCategories(List<String> categories, Pageable pageable);
    Page<Book> findByQueryAndCategories(String query, List<String> categories, Pageable pageable);
    Optional<Book> findById(Long id);
    List<String> findDistinctCategories();
}
