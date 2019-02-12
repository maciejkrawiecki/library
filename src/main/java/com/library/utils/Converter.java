package com.library.utils;

import com.library.model.client.AuthorTO;
import com.library.model.client.BookTO;
import com.library.model.server.Author;
import com.library.model.server.Book;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    public static AuthorTO convertAuthorToAuthorTO(Author author) {
        return new AuthorTO()
                .setId(author.getId())
                .setName(author.getName());
    }

    public static Author convertAuthorTOtoAuthor(AuthorTO authorTO) {
        return new Author()
                .setName(authorTO.getName());
    }

    public static List<AuthorTO> convertAuthorsToAuthorTOs(List<Author> authors) {
        return authors.stream().map(Converter::convertAuthorToAuthorTO).collect(Collectors.toList());
    }

    public static Book mapBookTOtoBook(BookTO bookTO) {
        return new Book()
                .setNumberOfPages(bookTO.getNumbersOfPages())
                .setTitle(bookTO.getTitle());
    }

    private static BookTO mapBookToBookTO(Book book) {
        return new BookTO()
                .setId(book.getId())
                .setNumbersOfPages(book.getNumberOfPages())
                .setTitle(book.getTitle())
                .setAuthorId(book.getAuthor().getId());
    }

    public static List<BookTO> mapBooksToBookTOs(List<Book> books) {
        return books.stream().map(Converter::mapBookToBookTO).collect(Collectors.toList());
    }


}
