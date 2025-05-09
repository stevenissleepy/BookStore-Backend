package fun.steven.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;

    /* method */
    public static <T> ResponseMessage<T> success(String message, T data) {
        return new ResponseMessage<>(200, message, data);
    }

    public static ResponseMessage<String> error(Integer code, String message) {
        return new ResponseMessage<>(code, message, null);
    }
}
