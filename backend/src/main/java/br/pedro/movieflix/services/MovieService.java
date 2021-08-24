package br.pedro.movieflix.services;

import br.pedro.movieflix.dtos.MovieDTO;
import br.pedro.movieflix.entities.Genre;
import br.pedro.movieflix.entities.Movie;
import br.pedro.movieflix.repositories.GenreRepository;
import br.pedro.movieflix.repositories.MovieRepository;
import br.pedro.movieflix.repositories.ReviewRepository;
import br.pedro.movieflix.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository repository;

    private final GenreRepository genreRepository;

    private final ReviewRepository reviewRepository;

    @Autowired
    MovieService(MovieRepository repository, GenreRepository genreRepository, ReviewRepository reviewRepository){
        this.repository = repository;
        this.genreRepository = genreRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional(readOnly = true)
    public Page<MovieDTO> findAllPaged(Pageable pageable, Long genreId){
        Genre genre = (genreId == 0L) ? null : genreRepository.getOne(genreId);
        Page<Movie> movies = repository.findFilteredPaged(pageable, genre);
        return movies.map(MovieDTO::new);
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id){
        Optional<Movie> opt = repository.findMovie(id);
        Movie movie = opt.orElseThrow(() -> new NotFoundException("Entity not found"));
        return new MovieDTO(movie, movie.getReviews());
    }
}
