package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.dto.ResponseMessage;
import fun.steven.bookstore.pojo.dto.stats.SalesDto;
import fun.steven.bookstore.pojo.dto.stats.SearchSalesDto;
import fun.steven.bookstore.service.IOrderService;
import fun.steven.bookstore.utils.annotation.AdminOnly;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private IOrderService orderService;

    @AdminOnly
    @PostMapping("/top-10-books")
    public ResponseMessage<SalesDto> searchTop10Books(@RequestBody SearchSalesDto searchSalesDto) {
        SalesDto salesDto = orderService.searchTop10Books(searchSalesDto);
        return ResponseMessage.success("get top 10 books success", salesDto);
    }

    @AdminOnly
    @PostMapping("/top-10-users")
    public ResponseMessage<SalesDto> searchTop10Users(@RequestBody SearchSalesDto searchSalesDto) {
        SalesDto salesDto = orderService.searchTop10Users(searchSalesDto);
        return ResponseMessage.success("get top 10 users success", salesDto);
    }
}
