package fun.steven.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.entity.BookStock;

@Repository
public interface BookStockRepository extends JpaRepository<BookStock, Long> {
    List<BookStock> findByIdIn(List<Long> ids);
}