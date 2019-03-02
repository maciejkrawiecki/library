package com.library.repository;

import com.library.model.server.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(@NotNull String name);

    Author findAuthorById(@NotNull Long authorId);
}
