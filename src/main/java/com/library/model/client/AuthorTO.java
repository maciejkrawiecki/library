package com.library.model.client;

import javax.validation.constraints.NotBlank;

public class AuthorTO {

    private Long id;
    @NotBlank
    private String name;

    public Long getId() {
        return id;
    }

    public AuthorTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthorTO setName(String name) {
        this.name = name;
        return this;
    }
}
