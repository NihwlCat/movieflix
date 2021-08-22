package br.pedro.movieflix.repositories;

import br.pedro.movieflix.entities.Genre;
import br.pedro.movieflix.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
