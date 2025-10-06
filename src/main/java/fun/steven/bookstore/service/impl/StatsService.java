package fun.steven.bookstore.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fun.steven.bookstore.dao.IOrderDao;
import fun.steven.bookstore.pojo.dto.book.FindBookResponse;
import fun.steven.bookstore.pojo.dto.stats.ResultDto;
import fun.steven.bookstore.pojo.dto.stats.ResultDto.ResultItemDto;
import fun.steven.bookstore.pojo.dto.stats.StatsAllRequest;
import fun.steven.bookstore.pojo.dto.stats.StatsUserRequest;
import fun.steven.bookstore.pojo.entity.Book;
import fun.steven.bookstore.service.IStatsService;

public class StatsService implements IStatsService {

    @Autowired
    private IOrderDao orderDao;

    @Override
    public ResultDto<FindBookResponse> statsUserBooks(StatsUserRequest request) {
        Long userId = request.getUserId();
        String startDateStr = request.getStartDate();
        String endDateStr = request.getEndDate();
        List<Object[]> bookList;

        if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            bookList = orderDao.findByUserId(userId);
        } else {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            bookList = orderDao.findByUserIdAndDateRange(userId, startDate, endDate);
        }

        List<ResultItemDto<FindBookResponse>> bookItems = bookList.stream()
                .map(item -> {
                    Book book = (Book) item[0];
                    Integer sales = ((Number) item[1]).intValue();
                    FindBookResponse bookDto = new FindBookResponse(book);
                    return new ResultItemDto<>(bookDto, sales);
                })
                .toList();
        return new ResultDto<>(bookItems);
    }

    @Override
    public ResultDto<String> searchTop10Books(StatsAllRequest request) {
        String startDateStr = request.getStartDate();
        String endDateStr = request.getEndDate();
        List<Object[]> salesList;

        if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            salesList = orderDao.findTop10Books();
        } else {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            salesList = orderDao.findTop10BooksByDateRange(startDate, endDate);
        }

        List<ResultItemDto<String>> salesItems = salesList.stream()
                .map(sale -> new ResultItemDto<>((String) sale[0], ((Number) sale[1]).intValue()))
                .toList();

        return new ResultDto<>(salesItems);
    }

    @Override
    public ResultDto<String> searchTop10Users(StatsAllRequest request) {
        String startDateStr = request.getStartDate();
        String endDateStr = request.getEndDate();
        List<Object[]> salesList;

        if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            salesList = orderDao.findTop10Users();
        } else {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            salesList = orderDao.findTop10UsersByDateRange(startDate, endDate);
        }

        List<ResultItemDto<String>> salesItems = salesList.stream()
                .map(sale -> new ResultItemDto<>((String) sale[0], ((Number) sale[1]).intValue()))
                .toList();
        return new ResultDto<>(salesItems);
    }
}
