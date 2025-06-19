package fun.steven.bookstore.pojo.dto.stats;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesDto {

    @Data
    @AllArgsConstructor
    public static class SalesItemDto {
        private String name;
        private Integer quantity;
    }

    List<SalesItemDto> salesItems;
}
