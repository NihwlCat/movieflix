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

    //@Query("SELECT movieObj FROM Movie movieObj INNER JOIN movieObj.genre genreObj WHERE :genre IS NULL OR :genre IN(genreObj)")
    @Query("SELECT movieObj FROM Movie movieObj INNER JOIN movieObj.genre genreObj WHERE COALESCE(:genre) IS NULL OR :genre = genreObj")
    Page<Movie> findFilteredPaged(Pageable pageable, Genre genre);
}
