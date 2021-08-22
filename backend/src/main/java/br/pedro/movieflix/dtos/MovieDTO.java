package br.pedro.movieflix.dtos;

import br.pedro.movieflix.entities.Movie;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieDTO {

    private Long id;
    private String title;
    private String subtitle;
    private Integer year;
    private String imgUrl;
    private String synopsis;
    private final List<ReviewDTO> reviews = new ArrayList<>();
    private Long genreId;

    public MovieDTO(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getSubtitle(), movie.getYear(), movie.getImgUrl(), movie.getSynopsis(), movie.getGenre().getId());
        reviews.addAll(movie.getReviews().stream().map(ReviewDTO::new).collect(Collectors.toList()));
    }

    public MovieDTO(Long id, String title, String subtitle, Integer year, String imgUrl, String synopsis, Long genreId) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.year = year;
        this.imgUrl = imgUrl;
        this.synopsis = synopsis;
        this.genreId = genreId;
    }

    public MovieDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
}
