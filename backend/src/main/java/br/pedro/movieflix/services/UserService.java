package br.pedro.movieflix.services;

import br.pedro.movieflix.entities.User;
import br.pedro.movieflix.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;

    @Autowired
    UserService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        User u = repository.findByEmail(email);
        if(u == null){
            logger.error("Username not found: " + email);
            throw new UsernameNotFoundException("User not found");
        }

        logger.info("User found: " + email);
        return u;
    }
}
