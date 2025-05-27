package fun.steven.bookstore.utils.exception;

public class CartEmptyException extends RuntimeException {
    public CartEmptyException() {
        super("购物车为空");
    }
}
