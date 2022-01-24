import { canReview, makePrivateRequest } from 'core/functions';
import { useCallback, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import MovieDetails from './MovieDetails';
import starImage from 'core/images/star.png'
import { Movie } from 'core/types';
import './style.css'

type Params = {
    movieId: string;
}

const MovieDescription = () => {
    const { movieId } = useParams<Params>();
    const [movie, setMovie] = useState<Movie>();

    const [newReview, setNewReview] = useState('');

    const submitReview = () => {
        if(newReview.trim()){
            const payload = {
                movieId,
                text: newReview
            }

            makePrivateRequest({ url: '/reviews', method: 'POST', data: payload })
            .then(() => getMovieDetails())
        } else {
            alert('Deve haver um texto na mensagem!')
        }
    }

    const getMovieDetails = useCallback(() => {
        makePrivateRequest({url: `/movies/${movieId}`})
        .then(r => setMovie(r.data))
    },[movieId]);

    useEffect(() => {
        getMovieDetails()
    }, [getMovieDetails])

    return <section className="section-container">
        {movie && (<MovieDetails movie={movie}/>)}
        {canReview() && (
            <div className="custom-border content-section" style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                <input className="input-review" value={newReview} onChange={event => setNewReview(event.target.value)}></input>
                <button className="primary-button" style={{ width: '400px', height: '50px' }} onClick={() => submitReview()}>SALVAR AVALIAÇÃO</button>
            </div>)}
        {(movie?.reviews && movie.reviews.length > 0) && (
            <div className="custom-border content-section">
            {movie.reviews.map(review => (
                <div className="review-content" key={review.id}>
                    <img src={starImage} alt="Estrela"></img>
                    <span>{review.userId}</span>
                    <p>{review.text}</p>
                </div>
            ))}
        </div>)}
    </section>
}

export default MovieDescription;