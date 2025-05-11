package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.dto.BookDto;
import fun.steven.bookstore.dto.ResponseMessage;
import fun.steven.bookstore.service.IBookService;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @PostMapping
    public ResponseMessage<String> add(@RequestBody BookDto bookDto) {
        bookService.add(bookDto);
        return ResponseMessage.success("add book success", null);
    }
}
