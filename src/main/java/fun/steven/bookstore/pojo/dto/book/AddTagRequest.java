package fun.steven.bookstore.pojo.dto.book;

import lombok.Data;

@Data
public class AddTagRequest {
    private Long id;
    private String tag;
}
