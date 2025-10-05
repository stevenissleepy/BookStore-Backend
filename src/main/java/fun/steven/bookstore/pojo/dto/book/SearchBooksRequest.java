package fun.steven.bookstore.pojo.dto.book;

import java.util.List;

import lombok.Data;

@Data
public class SearchBooksRequest {
    private String title;
    private List<String> categories;
    private Integer page;
    private Integer limit;
}
