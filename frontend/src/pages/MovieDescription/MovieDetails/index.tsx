import { Movie } from 'core/types';
import './style.css'

type Props = {
    movie: Movie;
}

const MovieDetails = ({ movie }: Props) => {
    return (<div className="custom-border moviedetails-container">
        <img alt={movie.title} src={movie.imgUrl}></img>
        <div className="moviedetails-content">
            <h3>{movie.title}</h3>
            <span>{movie.year}</span>
            <p>{movie.subtitle}</p>
            <p>{movie.synopsis}</p>
        </div>
    </div>)

}

export default MovieDetails;