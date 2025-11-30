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

import fun.steven.bookstore.pojo.ResponseMessage;
import fun.steven.bookstore.pojo.dto.book.AddBookRequest;
import fun.steven.bookstore.pojo.dto.book.FindBookResponse;
import fun.steven.bookstore.pojo.dto.book.FindBooksResponse;
import fun.steven.bookstore.pojo.dto.book.FindCategoriesResponse;
import fun.steven.bookstore.pojo.dto.book.SearchBooksRequest;
import fun.steven.bookstore.pojo.dto.book.UpdateBookRequest;
import fun.steven.bookstore.pojo.dto.book.AddTagRequest;
import fun.steven.bookstore.service.IBookService;
import fun.steven.bookstore.utils.annotation.AdminOnly;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @PostMapping
    public ResponseMessage<String> addBook(@RequestBody AddBookRequest request) {
        bookService.addBook(request);
        return ResponseMessage.success("add book success", null);
    }

    @PostMapping("/tag")
    public ResponseMessage<String> addTag(@RequestBody AddTagRequest request) {
        bookService.addTag(request);
        String message = "add tag {" + request.getTag() + "} to book {" + request.getId() + "}";
        return ResponseMessage.success(message, null);
    }

    @AdminOnly
    @PutMapping
    public ResponseMessage<String> update(@RequestBody UpdateBookRequest request) {
        bookService.updateBook(request);
        return ResponseMessage.success("update book success", null);
    }

    @AdminOnly
    @DeleteMapping("/{id}")
    public ResponseMessage<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseMessage.success("delete book success", null);
    }

    @PostMapping("/search")
    public ResponseMessage<FindBooksResponse> searchBooks(@RequestBody SearchBooksRequest request) {
        FindBooksResponse booksDto = bookService.searchBooks(request);
        return ResponseMessage.success("search book success", booksDto);
    }

    @GetMapping("/categories")
    public ResponseMessage<FindCategoriesResponse> findCategories() {
        FindCategoriesResponse response = bookService.findCategories();
        return ResponseMessage.success("get categories success", response);
    }

    @GetMapping("/{id}")
    public ResponseMessage<FindBookResponse> findBook(@PathVariable Long id) {
        FindBookResponse response = bookService.findBook(id);
        return ResponseMessage.success("get book success", response);
    }
}
