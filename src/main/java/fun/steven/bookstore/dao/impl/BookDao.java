package fun.steven.bookstore.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Caching(evict = {
            @CacheEvict(value = "book", key = "#result.id"),
            @CacheEvict(value = "books_page", allEntries = true)
    })
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "book", key = "#id"),
            @CacheEvict(value = "books_page", allEntries = true)
    })
    public boolean delete(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    @Override
    @Cacheable(value = "books_page", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAllBooks(pageable);
    }

    @Override
    public Page<Book> findByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitle(title, pageable);
    }

    @Override
    public Page<Book> findByCategories(List<String> categories, Pageable pageable) {
        return bookRepository.findByCategories(categories, pageable);
    }

    @Override
    public Page<Book> findByTitleAndCategories(String title, List<String> categories, Pageable pageable) {
        return bookRepository.findByTitleAndCategories(title, categories, pageable);
    }

    @Override
    public List<String> findDistinctCategories() {
        return bookRepository.findDistinctCategories();
    }

    @Override
    @Cacheable(value = "book", key = "#id", unless = "#result == null")
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}
