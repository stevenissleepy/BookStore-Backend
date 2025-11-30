package fun.steven.bookstore.pojo.dto.book;

import java.util.Set;

import org.springframework.beans.BeanUtils;

import fun.steven.bookstore.pojo.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindBookResponse {
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
    private Set<String> tags;

    public FindBookResponse(Book book){
        BeanUtils.copyProperties(book, this);
    }
}

