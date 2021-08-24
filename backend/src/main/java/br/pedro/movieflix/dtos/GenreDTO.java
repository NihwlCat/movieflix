package br.pedro.movieflix.dtos;

import br.pedro.movieflix.entities.Genre;

public class GenreDTO {
    private Long id;
    private String name;

    public GenreDTO() {
    }

    public GenreDTO(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public GenreDTO(Genre genre) {
        this(genre.getId(), genre.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
