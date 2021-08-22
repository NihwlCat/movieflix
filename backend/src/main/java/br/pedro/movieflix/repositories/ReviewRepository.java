package br.pedro.movieflix.repositories;

import br.pedro.movieflix.entities.Genre;
import br.pedro.movieflix.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
}
