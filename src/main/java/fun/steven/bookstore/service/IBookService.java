package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.book.BookDto;
import fun.steven.bookstore.dto.book.BooksDto;

public interface IBookService {
    boolean add(BookDto bookDto);
    boolean delete(Long id);
    boolean update(BookDto bookDto);
    BookDto get(Long id);
    BooksDto getAllBooks();
}
