package fun.steven.bookstore.entity;

import org.springframework.beans.BeanUtils;

import fun.steven.bookstore.dto.book.BookDto;
import fun.steven.bookstore.dto.book.UpdateBookDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Double price;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "cover", columnDefinition = "LONGTEXT")
    private String cover;

    @Column(name = "category")
    private String category;

    @Column(name = "language")
    private String language;

    @Column(name = "isbn")
    private String isbn;

    public Book(BookDto bookDto) {
        BeanUtils.copyProperties(bookDto, this);
    }

    public Book(UpdateBookDto bookDto) {
        BeanUtils.copyProperties(bookDto, this);
    }
}
