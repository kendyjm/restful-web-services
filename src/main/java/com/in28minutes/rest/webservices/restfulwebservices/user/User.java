package com.in28minutes.rest.webservices.restfulwebservices.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(value = "My description of User")
@Entity // JPA
@lombok.NoArgsConstructor
@lombok.Setter
@lombok.Getter
@lombok.ToString
public class User {
    @Id
    // avec GenerationType.Identity je m'assure que le prochain id inséré prendra en compte les données déjà existantes dans la bdd
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    @ApiModelProperty(value = "Name should have at least 2 characters")
    private String name;

    @Past
    @ApiModelProperty(value = "Birth date should be in the past")
    private Date birthDate;

    @OneToMany(mappedBy = "userPoster") // "userPoster" is a field in Post class
    private List<Post> posts;

    public User(@ApiParam(value = "My Id") Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
}
