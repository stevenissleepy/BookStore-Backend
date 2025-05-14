package fun.steven.bookstore.dto.cart;

import java.util.List;

import fun.steven.bookstore.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCartDto {
    List<GetCartItemDto> cart;

    public GetCartDto(Cart cart) {
        this.cart = cart.getCartItems().stream().map(GetCartItemDto::new).toList();
    }
}
