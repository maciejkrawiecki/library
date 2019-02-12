package com.library.controller;

import com.library.model.client.BookTO;
import com.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.library.utils.URLPaths.booksPath;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(booksPath)
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@Valid @RequestBody BookTO bookTO) {
        bookService.createBook(bookTO);
    }

    @GetMapping(booksPath)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<BookTO> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/api/books/{authorName}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<BookTO> getBooksByAuthor(@PathVariable String authorName) {
        return bookService.getBooksByAuthor(authorName);
    }
}
