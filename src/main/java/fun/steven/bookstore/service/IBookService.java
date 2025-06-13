package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.book.BooksDto;
import fun.steven.bookstore.pojo.dto.book.CategoriesDto;
import fun.steven.bookstore.pojo.dto.book.SalesDto;
import fun.steven.bookstore.pojo.dto.book.SearchBooksDto;
import fun.steven.bookstore.pojo.dto.book.SearchSalesDto;

public interface IBookService {
    boolean add(BookDto bookDto);
    boolean delete(Long id);
    boolean update(BookDto bookDto);
    BookDto get(Long id);
    BooksDto searchBooks(SearchBooksDto searchBooksDto);
    CategoriesDto getCategories();
    SalesDto searchSales(SearchSalesDto searchSalesDto);
}
