package com.library.service;

import com.library.exception.ResourceNotFoundException;
import com.library.model.client.BookTO;
import com.library.model.server.Author;
import com.library.model.server.Book;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.library.utils.Converter.convertBooksToBookTOs;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void createBook(BookTO bookTO) {
        Optional<Author> optionalAuthor = authorRepository.findById(bookTO.getAuthorId());

        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();

            Book book = new Book()
                    .setTitle(bookTO.getTitle())
                    .setNumberOfPages(bookTO.getNumbersOfPages())
                    .setAuthor(author);

            bookRepository.save(book);
        } else {
            throw new ResourceNotFoundException("Author", "id", bookTO.getAuthorId());
        }
    }

    public List<BookTO> getBooks() {
        return convertBooksToBookTOs(bookRepository.findAll());
    }

    public List<BookTO> getBooksByAuthor(String authorName) {
        return convertBooksToBookTOs(bookRepository.findAllByAuthor_Name(authorName));
    }
}
