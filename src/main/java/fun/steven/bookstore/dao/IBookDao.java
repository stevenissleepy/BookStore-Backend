package fun.steven.bookstore.dao;

import fun.steven.bookstore.entity.Book;

public interface IBookDao {
    boolean add(Book book);
    boolean delete(Long id);
    boolean update(Book book);
    Book get(Long id);
}
