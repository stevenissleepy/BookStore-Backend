package fun.steven.bookstore.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dao.IBookDao;
import fun.steven.bookstore.pojo.entity.Book;
import fun.steven.bookstore.repository.BookRepository;

@Repository
public class BookDao implements IBookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean save(Book book) {
        return bookRepository.save(book) != null;
    }

    @Override
    public boolean delete(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAllBooks(pageable);
    }

    @Override
    public Page<Book> findByQuery(String query, Pageable pageable) {
        return bookRepository.findByQuery(query, pageable);
    }

    @Override
    public Page<Book> findByCategories(List<String> categories, Pageable pageable) {
        return bookRepository.findByCategories(categories, pageable);
    }

    @Override
    public Page<Book> findByQueryAndCategories(String query, List<String> categories, Pageable pageable) {
        return bookRepository.findByQueryAndCategories(query, categories, pageable);
    }

    @Override
    public List<String> findDistinctCategories() {
        return bookRepository.findDistinctCategories();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}
