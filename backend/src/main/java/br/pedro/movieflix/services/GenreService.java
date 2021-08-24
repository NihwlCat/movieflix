package br.pedro.movieflix.services;

import br.pedro.movieflix.dtos.GenreDTO;
import br.pedro.movieflix.entities.Genre;
import br.pedro.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository repository;

    @Autowired
    GenreService(GenreRepository repository){
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<GenreDTO> findAll(){
        List<Genre> genres = repository.findAll();
        return genres.stream().map(GenreDTO::new).collect(Collectors.toList());
    }
}
