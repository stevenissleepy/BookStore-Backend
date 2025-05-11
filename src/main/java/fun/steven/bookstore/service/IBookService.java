package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.BookDto;
import fun.steven.bookstore.dto.UpdateBookDto;

public interface IBookService {
    boolean add(BookDto bookDto);
    boolean delete(Long id);
    boolean update(UpdateBookDto bookDto);
    BookDto get(Long id);
}
