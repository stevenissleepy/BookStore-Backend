package fun.steven.bookstore.pojo.entity;

import fun.steven.bookstore.pojo.dto.book.AddBookRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "tb_book_stock")
public class BookStock {
    @Column(name = "id")
    private Long id;

    @Column(name = "stock")
    private Integer stock;

    public BookStock(AddBookRequest request) {
        this.stock = request.getStock();
    }
}
