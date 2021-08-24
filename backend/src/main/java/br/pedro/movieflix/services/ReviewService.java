package br.pedro.movieflix.services;

import br.pedro.movieflix.dtos.ReviewDTO;
import br.pedro.movieflix.dtos.UserDTO;
import br.pedro.movieflix.entities.Movie;
import br.pedro.movieflix.entities.Review;
import br.pedro.movieflix.entities.User;
import br.pedro.movieflix.repositories.MovieRepository;
import br.pedro.movieflix.repositories.ReviewRepository;
import br.pedro.movieflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    private final UserRepository userRepository;

    private final MovieRepository movieRepository;

    @Autowired
    ReviewService(ReviewRepository repository, UserRepository userRepository, MovieRepository movieRepository){
        this.repository = repository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    private User getAuthenticated(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email);
    }

    private void dtoToPersisted(ReviewDTO dto, Review review){
        Movie movie = movieRepository.getOne(dto.getMovieId());
        review.setText(dto.getText());
        review.setMovie(movie);
        review.setUser(getAuthenticated());
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO dto){
        Review review = new Review();
        dtoToPersisted(dto, review);
        review = repository.save(review);

        return new ReviewDTO(review.getText(),review.getMovie().getId(), new UserDTO(review.getUser()));
    }
}
