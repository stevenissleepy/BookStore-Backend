package fun.steven.bookstore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IBookDao;
import fun.steven.bookstore.dao.IBookStockDao;
import fun.steven.bookstore.pojo.dto.book.AddBookRequest;
import fun.steven.bookstore.pojo.dto.book.FindBookResponse;
import fun.steven.bookstore.pojo.dto.book.FindBooksResponse;
import fun.steven.bookstore.pojo.dto.book.FindCategoriesResponse;
import fun.steven.bookstore.pojo.dto.book.SearchBooksRequest;
import fun.steven.bookstore.pojo.dto.book.UpdateBookRequest;
import fun.steven.bookstore.pojo.entity.Book;
import fun.steven.bookstore.pojo.entity.BookStock;
import fun.steven.bookstore.service.IBookService;

@Service
public class BookService implements IBookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private IBookDao bookDao;
    
    @Autowired
    private IBookStockDao bookStockDao;

    public boolean addBook(AddBookRequest request) {
        Book book = new Book(request);
        Book newBook = bookDao.save(book);
        BookStock bookStock = new BookStock(newBook.getId(), request.getStock());
        bookStockDao.save(bookStock);

        return true;
    }

    public boolean deleteBook(Long bookId) {
        Book book = bookDao.findById(bookId).orElseThrow(
                () -> new RuntimeException("Book not found: " + bookId));

        book.setDeleted(true);
        bookDao.save(book);
        return true;
    }

    public FindBookResponse findBook(Long bookId) {
        long startTime = System.currentTimeMillis();
        
        Book book = bookDao.findById(bookId).orElseThrow(
                () -> new RuntimeException("Book not found: " + bookId));

        long endTime = System.currentTimeMillis();
        logger.info("Find book by ID {} took {} ms", bookId, endTime - startTime);

        return new FindBookResponse(book);
    }

    public FindBooksResponse searchBooks(SearchBooksRequest request) {
        String title = request.getTitle();
        List<String> categories = request.getCategories();
        Integer page = request.getPage();
        Integer limit = request.getLimit();
        Pageable pageable = PageRequest.of(page, limit);

        Page<Book> bookPage;

        // 如果查询条件和分类都为空，返回所有书籍
        if ((title == null || title.trim().isEmpty()) &&
                (categories == null || categories.isEmpty())) {
            bookPage = bookDao.findAll(pageable);
        }

        // 如果只有查询条件，没有分类限制
        else if (categories == null || categories.isEmpty()) {
            title = (title == null) ? "" : title;
            bookPage = bookDao.findByTitle(title, pageable);
        }

        // 如果只有分类限制，没有查询条件
        else if (title == null || title.trim().isEmpty()) {
            bookPage = bookDao.findByCategories(categories, pageable);
        }

        // 如果查询条件和分类都有
        else {
            bookPage = bookDao.findByTitleAndCategories(title.trim(), categories, pageable);
        }

        // 查询 Stock
        Boolean withStock = request.getWithStock();
        if (withStock != null && withStock) {
            List<Long> ids = bookPage.stream().map(Book::getId).collect(Collectors.toList());
            List<BookStock> stocks = bookStockDao.findByIdIn(ids);
            Map<Long, BookStock> stockMap = stocks.stream().collect(Collectors.toMap(BookStock::getId, s -> s, (a, b) -> a));

            for (Book book : bookPage) {
                BookStock stock = stockMap.get(book.getId());
                book.setStock(stock.getStock());
            }
        }

        return new FindBooksResponse(bookPage);
    }

    public FindCategoriesResponse findCategories() {
        return new FindCategoriesResponse(bookDao.findDistinctCategories());
    }

    public boolean updateBook(UpdateBookRequest request) {
        Book updateBook = bookDao.findById(request.getId()).orElseThrow(
                () -> new RuntimeException("Book not found: " + request.getId()));

        BeanUtils.copyProperties(request, updateBook);
        bookDao.save(updateBook);
        return true;
    }
}
