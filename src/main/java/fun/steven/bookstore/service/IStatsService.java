package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.stats.ResultDto;
import fun.steven.bookstore.pojo.dto.stats.StatsAllRequest;
import fun.steven.bookstore.pojo.dto.stats.StatsUserRequest;
import fun.steven.bookstore.pojo.dto.book.FindBookResponse;

public interface IStatsService {
    ResultDto<FindBookResponse> statsUserBooks(StatsUserRequest request);

    ResultDto<String> searchTop10Books(StatsAllRequest request);

    ResultDto<String> searchTop10Users(StatsAllRequest request);
}
