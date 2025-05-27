package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IBookDao;
import fun.steven.bookstore.dto.book.BookDto;
import fun.steven.bookstore.dto.book.BooksDto;
import fun.steven.bookstore.dto.book.UpdateBookDto;
import fun.steven.bookstore.entity.Book;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookDao bookDao;

    public boolean add(BookDto bookDto) {
        Book book = new Book(bookDto);
        return bookDao.add(book);
    }

    public boolean delete(Long id) {
        return bookDao.delete(id);
    }

    public boolean update(UpdateBookDto bookDto) {
        Book book = new Book(bookDto);
        return bookDao.update(book);
    }

    public BookDto get(Long id) {
        return bookDao.getBookById(id);
    }

    public BooksDto getAllBooks() {
        return bookDao.getAllBooks();
    }
}
