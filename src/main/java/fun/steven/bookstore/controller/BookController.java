package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.dto.ResponseMessage;
import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.book.BooksDto;
import fun.steven.bookstore.service.IBookService;
import fun.steven.bookstore.utils.annotation.AdminOnly;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @AdminOnly
    @PostMapping
    public ResponseMessage<String> add(@RequestBody BookDto bookDto) {
        bookService.add(bookDto);
        return ResponseMessage.success("add book success", null);
    }

    @AdminOnly
    @PutMapping
    public ResponseMessage<String> update(@RequestBody BookDto bookDto) {
        bookService.update(bookDto);
        return ResponseMessage.success("update book success", null);
    }

    @AdminOnly
    @DeleteMapping("/{id}")
    public ResponseMessage<String> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseMessage.success("delete book success", null);
    }

    @GetMapping("/all")
    public ResponseMessage<BooksDto> getAllBooks() {
        BooksDto booksDto = bookService.getAllBooks();
        return ResponseMessage.success("get book success", booksDto);
    }

    @GetMapping("/{id}")
    public ResponseMessage<BookDto> getBookById(@PathVariable Long id) {
        BookDto bookDto = bookService.get(id);
        return ResponseMessage.success("get book success", bookDto);
    }
}
