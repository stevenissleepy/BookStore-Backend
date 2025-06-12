package fun.steven.bookstore.dao;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.book.BooksDto;
import fun.steven.bookstore.pojo.dto.book.SearchBooksDto;
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

    @Override
    public BooksDto searchBooks(SearchBooksDto searchBooksDto) {
        String query = searchBooksDto.getQuery();
        List<String> categories = searchBooksDto.getCategories();
        List<Book> books;

        // 如果查询条件和分类都为空，返回所有书籍
        if ((query == null || query.trim().isEmpty()) &&
                (categories == null || categories.isEmpty())) {
            books = bookRepository.findAll();
        }

        // 如果只有查询条件，没有分类限制
        else if (categories == null || categories.isEmpty()) {
            query = (query == null) ? "" : query;
            books = bookRepository.findByQuery(query).orElse(List.of());
        }

        // 如果只有分类限制，没有查询条件
        else if (query == null || query.trim().isEmpty()) {
            books = bookRepository.findByCategories(categories).orElse(List.of());
        }

        // 如果查询条件和分类都有
        else {
            books = bookRepository.findByQueryAndCategories(query.trim(), categories).orElse(List.of());
        }

        return new BooksDto(books);
    }
}
