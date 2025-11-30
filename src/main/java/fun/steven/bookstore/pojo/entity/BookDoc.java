package fun.steven.bookstore.pojo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "book_doc")
public class BookDoc {
    @Id
    private Long id;
    private String cover;
    private String description;

    public BookDoc(Book book) {
        this.id = book.getId();
        this.cover = book.getCover();
        this.description = book.getDescription();
    }
}
