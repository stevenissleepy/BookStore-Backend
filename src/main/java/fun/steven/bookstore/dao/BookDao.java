package fun.steven.bookstore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.Book;
import fun.steven.bookstore.repository.BookRepository;

@Repository
public class BookDao implements IBookDao {
    @Autowired
    private BookRepository bookRepository;

    public boolean add(Book book) {
        return bookRepository.save(book) != null;
    }

    public boolean delete(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    public boolean update(Book book) {
        Book updateBook = bookRepository.findById(book.getId()).orElseThrow(
                () -> new RuntimeException("Book not found"));

        updateBook.setTitle(book.getTitle());
        updateBook.setPrice(book.getPrice());
        bookRepository.save(updateBook);
        return true;
    }

    public Book get(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found"));
    }
}
