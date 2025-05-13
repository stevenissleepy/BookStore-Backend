package fun.steven.bookstore.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @brief 查询用户的购物车时返回的对象
 */
@Data
@AllArgsConstructor
public class GetCartDto {
    List<GetCartItemDto> cartItems;
}
