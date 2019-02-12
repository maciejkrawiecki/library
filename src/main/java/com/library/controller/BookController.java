package com.library.controller;

import com.library.model.client.BookTO;
import com.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

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
        //book service
    }
}
