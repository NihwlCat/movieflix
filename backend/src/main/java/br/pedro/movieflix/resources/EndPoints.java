package br.pedro.movieflix.resources;

import br.pedro.movieflix.dtos.GenreDTO;
import br.pedro.movieflix.dtos.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EndPoints {

    @GetMapping(value = "/genres")
    public ResponseEntity<List<GenreDTO>> findAllGenres(){
        System.out.println("/genres");
        return null;
    }

    @GetMapping(value = "/movies")
    public ResponseEntity<Page<MovieDTO>> findAllMovies(){
        System.out.println("/movies");
        return null;
    }

    @GetMapping(value = "/movies/{id}")
    public ResponseEntity<MovieDTO> findByMovieId(@PathVariable Long id){
        System.out.println("/movies/{id}");
        return null;
    }

    @PostMapping(value = "/reviews")
    public ResponseEntity<Void> insertReview(){
        System.out.println("/reviews");
        return null;
    }
}
