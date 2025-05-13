package fun.steven.bookstore.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IBookDao;
import fun.steven.bookstore.dto.book.BookDto;
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
        Book book = bookDao.getBookById(id);
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        return bookDto;
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookDao.getAllBooks();
        List<BookDto> bookDtos = books.stream().map(book -> {
            BookDto bookDto = new BookDto();
            BeanUtils.copyProperties(book, bookDto);
            return bookDto;
        }).toList();
        return bookDtos;
    }
}
