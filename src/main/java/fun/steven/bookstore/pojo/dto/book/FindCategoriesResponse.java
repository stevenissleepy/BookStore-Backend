package fun.steven.bookstore.pojo.dto.book;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCategoriesResponse {
    private List<String> categories;
}
