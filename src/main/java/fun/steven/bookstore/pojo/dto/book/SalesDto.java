package fun.steven.bookstore.pojo.dto.book;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesDto {

    @Data
    @AllArgsConstructor
    public static class SalesItemDto {
        private String title;
        private Integer sales;
    }

    List<SalesItemDto> salesItems;
}
