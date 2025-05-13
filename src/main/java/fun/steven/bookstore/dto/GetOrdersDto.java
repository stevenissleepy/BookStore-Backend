package fun.steven.bookstore.dto;

import java.util.List;

import lombok.Data;

@Data
public class GetOrdersDto {
    List<GetOrderDto> orders;
}
