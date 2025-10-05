package fun.steven.bookstore.pojo.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequest {
    private String title;
    private String author;
    private String description;
    private String category;
    private String language;
    private String isbn;
    private Integer price;
    private Integer stock;
    private String cover;
}
