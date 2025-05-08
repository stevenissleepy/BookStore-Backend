package fun.steven.bookstore.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "password")
    private String password;
}
