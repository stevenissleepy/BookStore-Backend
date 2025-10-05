package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.book.AddBookRequest;
import fun.steven.bookstore.pojo.dto.book.FindBooksResponse;
import fun.steven.bookstore.pojo.dto.book.FindCategoriesResponse;
import fun.steven.bookstore.pojo.dto.book.FindBookResponse;
import fun.steven.bookstore.pojo.dto.book.SearchBooksRequest;
import fun.steven.bookstore.pojo.dto.book.UpdateBookRequest;

public interface IBookService {
    boolean addBook(AddBookRequest request);

    boolean deleteBook(Long bookId);

    FindBookResponse findBook(Long bookId);

    FindBooksResponse searchBooks(SearchBooksRequest request);

    FindCategoriesResponse findCategories();

    boolean updateBook(UpdateBookRequest request);
}
