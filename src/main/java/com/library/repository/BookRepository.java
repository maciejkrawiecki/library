package com.library.repository;

import com.library.model.server.Author;
import com.library.model.server.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor(@NotNull Author author);

    List<Book> findAllByAuthor_Name(@NotNull String authorName);

    List<Book> findAllByTitle(@NotNull String title);
}
