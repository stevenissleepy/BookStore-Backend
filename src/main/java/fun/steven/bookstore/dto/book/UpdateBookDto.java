package fun.steven.bookstore.dto.book;

import lombok.Data;

@Data
public class UpdateBookDto {
    private Long id;
    private String title;
    private Integer price;
    private String cover;
}
