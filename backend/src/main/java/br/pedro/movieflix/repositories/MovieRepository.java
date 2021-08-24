package br.pedro.movieflix.repositories;

import br.pedro.movieflix.entities.Genre;
import br.pedro.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT movieObj FROM Movie movieObj INNER JOIN movieObj.genre genreObj WHERE :genre IS NULL OR :genre IN(genreObj)")
    Page<Movie> findFilteredPaged(Pageable pageable, Genre genre);


    @Query("SELECT movie FROM Movie movie JOIN FETCH movie.reviews reviews WHERE movie.id = :id")
    Optional<Movie> findMovie(Long id);
}
