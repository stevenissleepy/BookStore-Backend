package fun.steven.bookstore.pojo.dto.stats;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultDto<T> {

    @Data
    @AllArgsConstructor
    public static class ResultItemDto<T> {
        private T object;
        private Integer quantity;
    }

    List<ResultItemDto<T>> salesItems;
}
