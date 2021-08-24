package br.pedro.movieflix.dtos;

import br.pedro.movieflix.entities.Review;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ReviewDTO {

    private Long id;
    private String text;
    private String userId;
    private Long movieId;

    public ReviewDTO() {
    }

    public ReviewDTO(Review review){
        this(review.getId(), review.getText(), review.getUser().getEmail(), null);
    }

    public ReviewDTO(String text, Long movieId){
        this.text = text;
        this.movieId = movieId;
    }
    public ReviewDTO(Long id, String text, String userId, Long movieId) {
        this.id = id;
        this.text = text;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUser(String userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
