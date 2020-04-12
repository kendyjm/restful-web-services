package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    // GET /users
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsersUsers() {
        return service.findAll();
    }

    // GET /users/{id}
    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }

}