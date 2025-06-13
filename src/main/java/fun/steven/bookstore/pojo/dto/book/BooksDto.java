package fun.steven.bookstore.pojo.dto.book;

import java.util.List;

import org.springframework.data.domain.Page;

import fun.steven.bookstore.pojo.entity.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BooksDto {
    Integer quantity;
    List<BookDto> books;
    private Integer currentPage;
    private Boolean first;
    private Boolean last;

    public BooksDto(List<Book> books) {
        this.quantity = books.size();
        this.books = books.stream().map(BookDto::new).toList();
    }

    public BooksDto(Page<Book> page) {
        this.quantity = (int) page.getTotalElements();
        this.books = page.getContent().stream().map(BookDto::new).toList();
        this.currentPage = page.getNumber();
        this.first = page.isFirst();
        this.last = page.isLast();
    }
}
