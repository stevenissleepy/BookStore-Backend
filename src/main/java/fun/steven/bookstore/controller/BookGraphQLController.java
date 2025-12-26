package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.dto.book.FindBooksResponse;
import fun.steven.bookstore.pojo.dto.book.SearchBooksRequest;
import fun.steven.bookstore.service.IBookService;

@RestController
@RequestMapping("/graphql")
public class BookGraphQLController {
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_LIMIT = 10;

    @Autowired
    private IBookService bookService;

    @QueryMapping
    public FindBooksResponse booksByName(@Argument String name,
            @Argument Integer page,
            @Argument Integer limit) {
        SearchBooksRequest request = new SearchBooksRequest();
        request.setTitle(name);
        request.setPage(resolvePage(page));
        request.setLimit(resolveLimit(limit));
        return bookService.searchBooks(request);
    }

    private int resolvePage(Integer page) {
        return (page == null || page < 0) ? DEFAULT_PAGE : page;
    }

    private int resolveLimit(Integer limit) {
        return (limit == null || limit <= 0) ? DEFAULT_LIMIT : limit;
    }
}
