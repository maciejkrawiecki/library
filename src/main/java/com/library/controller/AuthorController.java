package com.library.controller;

import com.library.model.client.AuthorTO;
import com.library.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // valid checks if object is properly initialized
    @PostMapping("/api/authors")
    @ResponseStatus(CREATED)
    public void createAuthor(@Valid @RequestBody AuthorTO authorTO) {
        authorService.createAuthor(authorTO);
    }

    @GetMapping("/api/authors")
    @ResponseStatus(OK)
    public @ResponseBody
    List<AuthorTO> getAuthors() {
        return authorService.getAuthors();
    }
}