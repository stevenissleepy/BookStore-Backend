package fun.steven.bookstore.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.Book;
import fun.steven.bookstore.repository.BookRepository;

@Repository
public class BookDao implements IBookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean add(Book book) {
        return bookRepository.save(book) != null;
    }

    @Override
    public boolean delete(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(Book book) {
        Book updateBook = bookRepository.findById(book.getId()).orElseThrow(
                () -> new RuntimeException("Book not found"));

        updateBook.setTitle(book.getTitle());
        updateBook.setPrice(book.getPrice());
        bookRepository.save(updateBook);
        return true;
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found: " + id));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
