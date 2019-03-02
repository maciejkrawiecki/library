package com.library.service;

import com.library.model.client.AuthorTO;
import com.library.model.server.Author;
import com.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.library.utils.Converter.convertAuthorTOtoAuthor;
import static com.library.utils.Converter.convertAuthorsToAuthorTOs;

@Service
public class
AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // mapAuthorTOtoAuthor in utils.Mapper, maps authorTO to author
    public void createAuthor(AuthorTO authorTO) {
        Author author = convertAuthorTOtoAuthor(authorTO);
        authorRepository.save(author);
    }

    public List<AuthorTO> getAuthors() {
        return convertAuthorsToAuthorTOs(authorRepository.findAll());
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.delete(authorRepository.findAuthorById(authorId));
    }
}