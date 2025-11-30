package fun.steven.bookstore.pojo.entity;

import org.springframework.beans.BeanUtils;

import fun.steven.bookstore.pojo.dto.book.AddBookRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stock")
    private Integer stock;

    @Transient
    private String description;

    @Transient
    private String cover;

    @Column(name = "category")
    private String category;

    @Column(name = "language")
    private String language;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "deleted")
    private Boolean deleted = false;

    public Book(AddBookRequest request) {
        BeanUtils.copyProperties(request, this);
    }
}
