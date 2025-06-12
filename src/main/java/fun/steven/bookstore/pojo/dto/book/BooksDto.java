package fun.steven.bookstore.pojo.dto.book;

import java.util.List;

import fun.steven.bookstore.pojo.entity.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BooksDto {
    Integer quantity;
    List<BookDto> books;

    public BooksDto(List<Book> books) {
        this.quantity = books.size();
        this.books = books.stream().map(BookDto::new).toList();
    }
}
