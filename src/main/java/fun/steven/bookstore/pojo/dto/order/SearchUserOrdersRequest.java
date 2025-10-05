package fun.steven.bookstore.pojo.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchUserOrdersRequest {
    private Long userId;
    private String startDate;
    private String endDate;
    private String title;
    private Integer page;
    private Integer limit;
}
