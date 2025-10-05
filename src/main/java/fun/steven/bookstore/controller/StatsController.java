package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.ResponseMessage;
import fun.steven.bookstore.pojo.dto.book.FindBookResponse;
import fun.steven.bookstore.pojo.dto.stats.ResultDto;
import fun.steven.bookstore.pojo.dto.stats.StatsAllRequest;
import fun.steven.bookstore.pojo.dto.stats.StatsUserRequest;
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
    public ResponseMessage<ResultDto<String>> searchTop10Books(@RequestBody StatsAllRequest request) {
        ResultDto<String> salesDto = orderService.searchTop10Books(request);
        return ResponseMessage.success("get top 10 books success", salesDto);
    }

    @AdminOnly
    @PostMapping("/top-10-users")
    public ResponseMessage<ResultDto<String>> searchTop10Users(@RequestBody StatsAllRequest request) {
        ResultDto<String> salesDto = orderService.searchTop10Users(request);
        return ResponseMessage.success("get top 10 users success", salesDto);
    }

    @PostMapping("/books")
    public ResponseMessage<ResultDto<FindBookResponse>> statsBooks(
            @CurrentUserId Long userId,
            @RequestBody StatsUserRequest request) {
        request.setUserId(userId);
        ResultDto<FindBookResponse> response = orderService.statsBooks(request);
        return ResponseMessage.success("get books stats success", response);
    }
}
