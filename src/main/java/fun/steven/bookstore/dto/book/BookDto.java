package fun.steven.bookstore.dto.book;

import org.springframework.beans.BeanUtils;

import fun.steven.bookstore.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String description;
    private String category;
    private String language;
    private String isbn;
    private Integer price;
    private Integer stock;
    private String cover;

    public BookDto(Book book){
        BeanUtils.copyProperties(book, this);
    }
}
