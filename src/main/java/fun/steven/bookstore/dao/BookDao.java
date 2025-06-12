package fun.steven.bookstore.dao;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.book.BooksDto;
import fun.steven.bookstore.pojo.entity.Book;
import fun.steven.bookstore.repository.BookRepository;

@Repository
public class BookDao implements IBookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean add(BookDto bookDto) {
        Book book = new Book(bookDto);
        return bookRepository.save(book) != null;
    }

    @Override
    public boolean delete(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(BookDto bookDto) {
        Book updateBook = bookRepository.findById(bookDto.getId()).orElseThrow(
                () -> new RuntimeException("Book not found"));

        BeanUtils.copyProperties(bookDto, updateBook);
        bookRepository.save(updateBook);
        return true;
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found: " + id));
        return new BookDto(book);
    }

    @Override
    public BooksDto getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return new BooksDto(books);
    }
}
