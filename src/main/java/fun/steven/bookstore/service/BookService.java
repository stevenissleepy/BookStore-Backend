package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IBookDao;
import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.book.BooksDto;
import fun.steven.bookstore.pojo.dto.book.CategoriesDto;
import fun.steven.bookstore.pojo.dto.book.SearchBooksDto;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookDao bookDao;

    public boolean add(BookDto bookDto) {
        return bookDao.add(bookDto);
    }

    public boolean delete(Long id) {
        return bookDao.delete(id);
    }

    public boolean update(BookDto bookDto) {
        return bookDao.update(bookDto);
    }

    public BookDto get(Long id) {
        return bookDao.getBookById(id);
    }

    public BooksDto searchBooks(SearchBooksDto searchBooksDto) {        
        return bookDao.searchBooks(searchBooksDto);
    }

    public CategoriesDto getCategories() {
        return bookDao.getCategories();
    }
}
