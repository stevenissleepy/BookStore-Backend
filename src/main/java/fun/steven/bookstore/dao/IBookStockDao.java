package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.pojo.entity.BookStock;

public interface IBookStockDao {
    BookStock save(BookStock bookStock);

    List<BookStock> findByIdIn(List<Long> ids);
}
