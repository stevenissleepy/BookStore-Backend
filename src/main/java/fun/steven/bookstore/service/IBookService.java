package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.book.BooksDto;
import fun.steven.bookstore.pojo.dto.book.CategoriesDto;
import fun.steven.bookstore.pojo.dto.book.SearchBooksDto;

public interface IBookService {
    boolean add(BookDto bookDto);
    boolean delete(Long id);
    boolean update(BookDto bookDto);
    BookDto get(Long id);
    BooksDto getAllBooks();
    BooksDto searchBooks(SearchBooksDto searchBooksDto);
    CategoriesDto getCategories();
}
