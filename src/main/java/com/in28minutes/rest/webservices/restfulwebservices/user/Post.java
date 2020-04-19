package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.ToString
@Entity
public class Post {
    @Id
    // avec GenerationType.Identity je m'assure que le prochain id inséré prendra en compte les données déjà existantes dans la bdd
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore // pour éviter qu'à l'affichage d'un user et donc de son field 'posts' il n'y ait récursivité infinie...
    private User userPoster;
}
