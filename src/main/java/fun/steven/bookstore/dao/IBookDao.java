package fun.steven.bookstore.dao;

import fun.steven.bookstore.dto.book.BookDto;
import fun.steven.bookstore.dto.book.BooksDto;
import fun.steven.bookstore.entity.Book;

public interface IBookDao {
    boolean add(Book book);
    boolean delete(Long id);
    boolean update(Book book);
    BookDto getBookById(Long id);
    BooksDto getAllBooks();
}
