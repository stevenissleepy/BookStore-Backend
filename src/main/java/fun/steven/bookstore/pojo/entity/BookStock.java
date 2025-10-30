package fun.steven.bookstore.pojo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_book_stock")
public class BookStock {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "stock")
    private Integer stock;
}
