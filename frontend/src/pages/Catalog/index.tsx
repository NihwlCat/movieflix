import Pagination from 'core/components/Pagination';
import { makePrivateRequest } from 'core/functions';
import { ContentResponse, Genre } from 'core/types';
import { useCallback, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import MovieCard from './components'
import './style.css'

const Catalog = () => {
    const [activePage, setActivePage] = useState(0)
    const [contentResponse, setContentResponse] = useState<ContentResponse>()
    const [genres, setGenres] = useState<Genre[]>([])
    const [genreId, setGenreId] = useState(0)

    const getMovies = useCallback(() => {
        const params = {
            page: activePage,
            size: 4,
            genreId: genreId
        }
        makePrivateRequest({url: '/movies', params,})
        .then(r => setContentResponse(r.data))

    },[activePage, genreId])

    const handleSelectChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setGenreId(Number(event.target.value))
        setActivePage(0)
        getMovies()
    }

    useEffect(() => {
        getMovies()
        makePrivateRequest({url: '/genres'})
        .then(r => {
            const items = r.data as Genre[]
            items.push({ id: 0, name: 'Todos os gêneros' })
            setGenres(items)
        })
    },[getMovies])


    return <section className="section-container">
        <div className="custom-border catalog-searchbar">
            {genres && (
                <select onChange={handleSelectChange} value={genreId}>
                    {genres.map(genre => (
                        <option value={genre.id} key={genre.id}>{genre.name}</option>
                    ))}
                </select>)}
        </div>
        <main className="catalog-content">
            {contentResponse?.content && contentResponse.content.map(movie => (
                <Link to={`/movies/${movie.id}`} key={movie.id}>
                    <MovieCard movie={movie}/>
                </Link>
            ))}
        </main>

        {contentResponse && <Pagination activePage={activePage} totalPages={contentResponse?.totalPages} onChange={page => setActivePage(page)}/>}
    </section>
}

export default Catalog;