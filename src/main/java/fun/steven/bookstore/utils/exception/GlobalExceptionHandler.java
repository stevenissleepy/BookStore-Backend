package fun.steven.bookstore.utils.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fun.steven.bookstore.dto.ResponseMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /* 全局异常 */
    @ExceptionHandler({ RuntimeException.class })
    public ResponseMessage<Object> handleException(RuntimeException e) {
        String message = e.getMessage();
        logger.error("Global Exception: ", e);
        ResponseMessage<Object> response = new ResponseMessage<Object>(500, message, null);
        return response;
    }

    /* 登录异常 */
    @ExceptionHandler({ LoginException.class })
    public ResponseMessage<Object> handleLoginException(LoginException e) {
        String message = e.getMessage();
        logger.error("Login Exception: ", e);
        ResponseMessage<Object> response = new ResponseMessage<Object>(401, message, null);
        return response;
    }

    /* 购物车为空异常 */
    @ExceptionHandler({ CartEmptyException.class })
    public ResponseMessage<Object> handleCartEmptyException(CartEmptyException e) {
        String message = e.getMessage();
        logger.error("Cart Empty Exception: ", e);
        ResponseMessage<Object> response = new ResponseMessage<Object>(400, message, null);
        return response;
    }
}
