package fun.steven.bookstore.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.entity.BookDoc;

@Repository
public interface BookDocRepository extends MongoRepository<BookDoc, Long> {
    List<BookDoc> findByIdIn(Collection<Long> ids);
}
