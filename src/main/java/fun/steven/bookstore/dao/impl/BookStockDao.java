package fun.steven.bookstore.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dao.IBookStockDao;
import fun.steven.bookstore.pojo.entity.BookStock;
import fun.steven.bookstore.repository.BookStockRepository;

@Repository
public class BookStockDao implements IBookStockDao {
    @Autowired
    private BookStockRepository bookStockRepository;

    @Override
    public BookStock save(BookStock bookStock) {
        return bookStockRepository.save(bookStock);
    }

    @Override
    public List<BookStock> findByIdIn(List<Long> ids) {
        return bookStockRepository.findByIdIn(ids);
    }
}
