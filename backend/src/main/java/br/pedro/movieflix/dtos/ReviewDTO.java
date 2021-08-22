package br.pedro.movieflix.dtos;

import br.pedro.movieflix.entities.Review;
import br.pedro.movieflix.entities.User;

public class ReviewDTO {

    private Long id;
    private String text;
    private User user;
    private Long movieId;

    public ReviewDTO() {
    }

    public ReviewDTO(Review review){
        this(review.getId(), review.getText(), review.getUser(), review.getMovie().getId());
    }

    public ReviewDTO(Long id, String text, User user, Long movieId) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.movieId = movieId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
