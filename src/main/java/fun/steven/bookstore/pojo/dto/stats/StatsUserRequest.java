package fun.steven.bookstore.pojo.dto.stats;

import lombok.Data;

@Data
public class StatsUserRequest {
    Long userId;
    private String startDate;
    private String endDate;
}
