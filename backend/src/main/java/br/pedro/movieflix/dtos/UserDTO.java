package br.pedro.movieflix.dtos;

import br.pedro.movieflix.entities.User;

public class UserDTO {
    private String name;
    private String email;
    private Long id;

    public UserDTO() {
    }

    public UserDTO(String name, String email, Long id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public UserDTO(User entity){
        this(entity.getName(), entity.getEmail(), entity.getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }
}
