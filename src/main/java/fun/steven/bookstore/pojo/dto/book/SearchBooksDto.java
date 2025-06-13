package fun.steven.bookstore.pojo.dto.book;

import java.util.List;

import lombok.Data;

@Data
public class SearchBooksDto {
    private String query;
    private List<String> categories;
    private Integer page;
    private Integer limit;
}
