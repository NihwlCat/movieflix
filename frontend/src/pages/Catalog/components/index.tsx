import './style.css'
import { Movie } from 'core/types'

type Props = {
    movie: Movie;
}
const MovieCard = ({ movie } :Props) => {
    return <div className="custom-border moviecard-container">
        <img alt="Imagem" src={movie.imgUrl}></img>
        <div className="moviecard-content">
            <h5>{movie.title}</h5>
            <span>{movie.year}</span>
            <p>{movie.subtitle}</p>
        </div>
    </div>
}

export default MovieCard;