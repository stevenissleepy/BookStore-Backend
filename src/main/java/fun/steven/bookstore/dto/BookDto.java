package fun.steven.bookstore.dto;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private String description;
    private String category;
    private String language;
    private String isbn;
    private Double price;
    private String cover;
}
