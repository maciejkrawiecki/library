package com.library.model.client;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookTO {

    private Long id;
    @NotBlank
    private String title;
    @NotNull
    private Integer numbersOfPages;
    @NotNull
    private Long authorId;

    public Long getId() {
        return id;
    }

    public BookTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getNumbersOfPages() {
        return numbersOfPages;
    }

    public BookTO setNumbersOfPages(Integer numbersOfPages) {
        this.numbersOfPages = numbersOfPages;
        return this;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public BookTO setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }
}
