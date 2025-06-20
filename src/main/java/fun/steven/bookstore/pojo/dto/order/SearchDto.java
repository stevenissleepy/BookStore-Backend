package fun.steven.bookstore.pojo.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchDto {
    private String startDate;
    private String endDate;
    private String BookTitle;
    private Integer page;
    private Integer limit;
}
