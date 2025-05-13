package fun.steven.bookstore.dto.order;

import java.util.List;

import lombok.Data;

@Data
public class GetOrdersDto {
    List<GetOrderDto> orders;
}
