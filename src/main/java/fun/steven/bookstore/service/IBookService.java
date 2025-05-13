package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.book.BookDto;
import fun.steven.bookstore.dto.book.UpdateBookDto;

public interface IBookService {
    boolean add(BookDto bookDto);
    boolean delete(Long id);
    boolean update(UpdateBookDto bookDto);
    BookDto get(Long id);
}
