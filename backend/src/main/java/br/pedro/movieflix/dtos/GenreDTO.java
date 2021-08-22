package br.pedro.movieflix.dtos;

import br.pedro.movieflix.entities.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenreDTO {
    private Long id;
    private String name;
    private final List<MovieDTO> movies = new ArrayList<>();

    public GenreDTO() {
    }

    public GenreDTO(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public GenreDTO(Genre genre) {
        this(genre.getId(), genre.getName());

        movies.addAll(genre.getMovies().stream().map(MovieDTO::new).collect(Collectors.toList()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
