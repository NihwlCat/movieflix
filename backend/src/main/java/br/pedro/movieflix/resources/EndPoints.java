package br.pedro.movieflix.resources;

import br.pedro.movieflix.dtos.GenreDTO;
import br.pedro.movieflix.dtos.MovieDTO;
import br.pedro.movieflix.dtos.ReviewDTO;
import br.pedro.movieflix.services.GenreService;
import br.pedro.movieflix.services.MovieService;
import br.pedro.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EndPoints {

    private final GenreService genreService;

    private final MovieService movieService;

    private final ReviewService reviewService;

    @Autowired
    EndPoints(GenreService genreService, MovieService movieService, ReviewService reviewService){
        this.genreService = genreService;
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @GetMapping(value = "/genres")
    public ResponseEntity<List<GenreDTO>> findAllGenres(){
        List<GenreDTO> objects = genreService.findAll();
        return ResponseEntity.ok(objects);
    }

    @GetMapping(value = "/movies")
    public ResponseEntity<Page<MovieDTO>> findAllMovies(Pageable pageable, @RequestParam(value = "genreId", defaultValue = "0") Long genreId){
        PageRequest request = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));
        Page<MovieDTO> movies = movieService.findAllPaged(request, genreId);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/movies/{id}")
    public ResponseEntity<MovieDTO> findByMovieId(@PathVariable Long id){
        MovieDTO object = movieService.findById(id);
        return ResponseEntity.ok(object);
    }

    @PostMapping(value = "/reviews")
    public ResponseEntity<ReviewDTO> insertReview(@Valid @RequestBody ReviewDTO dto){
        ReviewDTO reviewDTO = reviewService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(reviewDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(reviewDTO);
    }
}
