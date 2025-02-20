package com.mjc.school.repository.model;

import java.util.Objects;

public class AuthorModel implements BaseEntity<Long> {
    private Long id;
    private String name;
    public AuthorModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorModel that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
