package fun.steven.bookstore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fun.steven.bookstore.entity.ResponseMessage;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    @ExceptionHandler({ Exception.class })
    public ResponseMessage<Object> handleException(Exception e) {
        logger.error("Global Exception: ", e);

        ResponseMessage<Object> response = new ResponseMessage<Object>(500, e.getMessage(), null);
        return response;
    }
}
