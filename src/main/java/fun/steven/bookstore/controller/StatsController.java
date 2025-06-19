package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.dto.ResponseMessage;
import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.stats.ResultDto;
import fun.steven.bookstore.pojo.dto.stats.DateRangeDto;
import fun.steven.bookstore.service.IOrderService;
import fun.steven.bookstore.utils.annotation.AdminOnly;
import fun.steven.bookstore.utils.annotation.CurrentUserId;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private IOrderService orderService;

    @AdminOnly
    @PostMapping("/top-10-books")
    public ResponseMessage<ResultDto<String>> searchTop10Books(@RequestBody DateRangeDto dateRangeDto) {
        ResultDto<String> salesDto = orderService.searchTop10Books(dateRangeDto);
        return ResponseMessage.success("get top 10 books success", salesDto);
    }

    @AdminOnly
    @PostMapping("/top-10-users")
    public ResponseMessage<ResultDto<String>> searchTop10Users(@RequestBody DateRangeDto dateRangeDto) {
        ResultDto<String> salesDto = orderService.searchTop10Users(dateRangeDto);
        return ResponseMessage.success("get top 10 users success", salesDto);
    }

    @PostMapping("/books")
    public ResponseMessage<ResultDto<BookDto>> statsBooks(
            @CurrentUserId Long userId,
            @RequestBody DateRangeDto dateRangeDto) {
        ResultDto<BookDto> salesDto = orderService.statsBooks(userId, dateRangeDto);
        return ResponseMessage.success("get books stats success", salesDto);
    }
}
