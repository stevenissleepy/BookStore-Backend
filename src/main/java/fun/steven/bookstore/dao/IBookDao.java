package fun.steven.bookstore.dao;

import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.book.BooksDto;
import fun.steven.bookstore.pojo.dto.book.CategoriesDto;
import fun.steven.bookstore.pojo.dto.book.SearchBooksDto;

public interface IBookDao {
    boolean add(BookDto book);
    boolean delete(Long id);
    boolean update(BookDto book);
    BookDto getBookById(Long id);
    BooksDto getAllBooks();
    BooksDto searchBooks(SearchBooksDto searchBooksDto);
    CategoriesDto getCategories();
}
