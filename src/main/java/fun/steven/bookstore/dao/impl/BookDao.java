package fun.steven.bookstore.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dao.IBookDao;
import fun.steven.bookstore.pojo.entity.Book;
import fun.steven.bookstore.pojo.entity.BookDoc;
import fun.steven.bookstore.repository.BookDocRepository;
import fun.steven.bookstore.repository.BookRepository;

@Repository
public class BookDao implements IBookDao {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookDocRepository bookDocRepository;

    @Override
    public boolean save(Book book) {
        boolean bookSaved = bookRepository.save(book) != null;
        boolean docSaved = bookDocRepository.save(new BookDoc(book)) != null;
        return bookSaved && docSaved;
    }

    @Override
    public boolean delete(Long id) {
        bookRepository.deleteById(id);
        bookDocRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        Page<Book> books = bookRepository.findAllBooks(pageable);
        populateBooksDoc(books.getContent());
        return books;
    }

    @Override
    public Page<Book> findByTitle(String title, Pageable pageable) {
        Page<Book> books = bookRepository.findByTitle(title, pageable);
        populateBooksDoc(books.getContent());
        return books;
    }

    @Override
    public Page<Book> findByCategories(List<String> categories, Pageable pageable) {
        Page<Book> books = bookRepository.findByCategories(categories, pageable);
        populateBooksDoc(books.getContent());
        return books;
    }

    @Override
    public Page<Book> findByTitleAndCategories(String title, List<String> categories, Pageable pageable) {
        Page<Book> books = bookRepository.findByTitleAndCategories(title, categories, pageable);
        populateBooksDoc(books.getContent());
        return books;
    }

    @Override
    public List<String> findDistinctCategories() {
        return bookRepository.findDistinctCategories();
    }

    @Override
    public Optional<Book> findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(this::populateBookDoc);
        return book;
    }

    private void populateBookDoc(Book book) {
        bookDocRepository.findById(book.getId())
                .ifPresent(bookDoc -> {
                    book.setCover(bookDoc.getCover());
                    book.setDescription(bookDoc.getDescription());
                });
    }

    private void populateBooksDoc(List<Book> books) {
        List<Long> bookIds = books.stream().map(Book::getId)
                .collect(Collectors.toList());
        Map<Long, BookDoc> bookDocMap = bookDocRepository.findByIdIn(bookIds).stream()
                .collect(Collectors.toMap(BookDoc::getId, bookDoc -> bookDoc));
        books.forEach(book -> {
            BookDoc bookDoc = bookDocMap.get(book.getId());
            if (bookDoc != null) {
                book.setCover(bookDoc.getCover());
                book.setDescription(bookDoc.getDescription());
            }
        });
    }
}
