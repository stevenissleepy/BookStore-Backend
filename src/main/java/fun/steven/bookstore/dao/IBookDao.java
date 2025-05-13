package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.entity.Book;

public interface IBookDao {
    boolean add(Book book);
    boolean delete(Long id);
    boolean update(Book book);
    Book getBookById(Long id);
    List<Book> getAllBooks();
}
