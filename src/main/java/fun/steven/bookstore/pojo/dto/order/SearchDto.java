package fun.steven.bookstore.pojo.dto.order;

import lombok.Data;

@Data
public class SearchDto {
    private String startDate;
    private String endDate;
    private String BookTitle;
}
