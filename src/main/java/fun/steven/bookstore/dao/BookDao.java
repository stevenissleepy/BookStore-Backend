package fun.steven.bookstore.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.book.BooksDto;
import fun.steven.bookstore.pojo.dto.book.CategoriesDto;
import fun.steven.bookstore.pojo.dto.book.SalesDto;
import fun.steven.bookstore.pojo.dto.book.SearchBooksDto;
import fun.steven.bookstore.pojo.dto.book.SearchSalesDto;
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
    public BooksDto searchBooks(SearchBooksDto searchBooksDto) {
        String query = searchBooksDto.getQuery();
        List<String> categories = searchBooksDto.getCategories();
        Integer page = searchBooksDto.getPage();
        Integer limit = searchBooksDto.getLimit();

        Pageable pageable = PageRequest.of(page, limit);
        Page<Book> bookPage;

        // 如果查询条件和分类都为空，返回所有书籍
        if ((query == null || query.trim().isEmpty()) &&
                (categories == null || categories.isEmpty())) {
            bookPage = bookRepository.findAll(pageable);
        }

        // 如果只有查询条件，没有分类限制
        else if (categories == null || categories.isEmpty()) {
            query = (query == null) ? "" : query;
            bookPage = bookRepository.findByQuery(query, pageable);
        }

        // 如果只有分类限制，没有查询条件
        else if (query == null || query.trim().isEmpty()) {
            bookPage = bookRepository.findByCategories(categories, pageable);
        }

        // 如果查询条件和分类都有
        else {
            bookPage = bookRepository.findByQueryAndCategories(query.trim(), categories, pageable);
        }

        return new BooksDto(bookPage);
    }

    @Override
    public CategoriesDto getCategories() {
        List<String> categories = bookRepository.findDistinctCategories();
        return new CategoriesDto(categories);
    }

    @Override
    public SalesDto searchSales(SearchSalesDto searchSalesDto) {
        String startDateStr = searchSalesDto.getStartDate();
        String endDateStr = searchSalesDto.getEndDate();
        List<Object[]> salesList;

        if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            salesList = bookRepository.findTop10BookSales();
        } else {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            salesList = bookRepository.findTop10BookSalesByDateRange(startDate, endDate);
        }

        List<SalesDto.SalesItemDto> salesItems = salesList.stream()
                .map(sale -> new SalesDto.SalesItemDto((String) sale[0], ((Number) sale[1]).intValue()))
                .toList();

        return new SalesDto(salesItems);
    }
}
