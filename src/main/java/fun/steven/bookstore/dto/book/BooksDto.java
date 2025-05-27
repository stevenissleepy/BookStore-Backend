package fun.steven.bookstore.dto.book;

import java.util.List;

import fun.steven.bookstore.entity.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BooksDto {
    List<BookDto> books;

    public BooksDto(List<Book> books) {
        this.books = books.stream().map(BookDto::new).toList();
    }
}
