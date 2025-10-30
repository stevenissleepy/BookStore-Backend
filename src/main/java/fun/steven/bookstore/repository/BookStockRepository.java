package fun.steven.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.entity.BookStock;

@Repository
public interface BookStockRepository extends JpaRepository<BookStock, Long> {
}