package br.pedro.movieflix.services.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String msg) {
        super(msg);
    }
}
