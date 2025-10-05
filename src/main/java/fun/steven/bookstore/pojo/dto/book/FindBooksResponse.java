package fun.steven.bookstore.pojo.dto.book;

import java.util.List;

import org.springframework.data.domain.Page;

import fun.steven.bookstore.pojo.entity.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindBooksResponse {
    Integer quantity;
    List<FindBookResponse> books;
    private Integer currentPage;
    private Boolean first;
    private Boolean last;

    public FindBooksResponse(Page<Book> page) {
        this.quantity = (int) page.getTotalElements();
        this.books = page.getContent().stream().map(FindBookResponse::new).toList();
        this.currentPage = page.getNumber();
        this.first = page.isFirst();
        this.last = page.isLast();
    }
}
