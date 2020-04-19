package com.in28minutes.rest.webservices.restfulwebservices.user;

import lombok.ToString;

import javax.persistence.*;

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
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User userPoster;
}
