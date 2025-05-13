package fun.steven.bookstore.dto.book;

import lombok.Data;

@Data
public class UpdateBookDto {
    private Long id;
    private String title;
    private Double price;
    private String cover;
}
